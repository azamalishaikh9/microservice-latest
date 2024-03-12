package com.ali.swipeservice.service;

import com.ali.swipeservice.exception.ServiceException;
import com.ali.swipeservice.model.Swipe;
import com.ali.swipeservice.model.constant.AttendanceStatus;
import com.ali.swipeservice.model.constant.SwipeType;
import com.ali.swipeservice.model.dto.request.SwipeRequest;
import com.ali.swipeservice.model.dto.response.AttendanceResult;
import com.ali.swipeservice.model.dto.response.SwipeResponse;
import com.ali.swipeservice.repository.SwipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Azam
 */

@Service
@RequiredArgsConstructor
public class SwipeService {

    private final SwipeRepository swipeRepository;

    private final KafkaTemplate<String, HashMap<String, AttendanceResult>> kafkaTemplate;

    public SwipeResponse saveSwipeDetails(SwipeRequest swipeRequest) {

        try {

            long countByEmpId = swipeRepository.countByEmpId(swipeRequest.getEmpId());
            if (countByEmpId % 2 == 0 && swipeRequest.getSwipeType().equals(SwipeType.OUT)) {
                throw new ServiceException("Please swipe in first..");
            }

            if (countByEmpId % 2 != 0 && swipeRequest.getSwipeType().equals(SwipeType.IN)) {
                throw new ServiceException("Please swipe out first..");
            }

            Swipe swipe = new Swipe();
            swipe.setSwipeTime(LocalDateTime.now());
            swipe.setEmpId(swipeRequest.getEmpId());
            swipe.setSwipeType(swipeRequest.getSwipeType());
            swipe.setCreatedAt(LocalDateTime.now());

            Swipe savedSwipeData = swipeRepository.save(swipe);
            return new SwipeResponse(savedSwipeData.getEmpId(), savedSwipeData.getSwipeTime());
        } catch (Exception ex) {
            throw new ServiceException("Caught exception while saving swipe details : ", ex.getMessage());
        }


    }

    public HashMap<String, AttendanceResult> calculateTotalHours() {
        List<Swipe> swipes = this.getSwipesByCreatedAt();

        Map<String, List<Swipe>> swipesByEmpId = swipes.stream().collect(Collectors.groupingBy(Swipe::getEmpId, Collectors.toList()));

        HashMap<String, AttendanceResult> attendanceStatusMap = new HashMap<>();

        for (Map.Entry<String, List<Swipe>> entry : swipesByEmpId.entrySet()) {
            String empId = entry.getKey();
            List<Swipe> swipesByEmpIdAndSwipeTime = entry.getValue();

            Duration totalDuration = Duration.ZERO;

            for (int i = 0; i <= swipesByEmpIdAndSwipeTime.size(); i += 2) {
                LocalDateTime swipeInTime = swipesByEmpIdAndSwipeTime.get(i).getSwipeTime();
                LocalDateTime swipeOutTime = swipesByEmpIdAndSwipeTime.get(i + 1).getSwipeTime();
                totalDuration = totalDuration.plus(Duration.between(swipeInTime, swipeOutTime));
            }

            double totalHours = totalDuration.toHours();

            AttendanceResult attendanceResult = getAttendanceResult(empId, totalHours);

            attendanceStatusMap.put(empId, attendanceResult);
        }

        kafkaTemplate.send("attendanceResult", attendanceStatusMap);
        return attendanceStatusMap;

    }

    private static AttendanceResult getAttendanceResult(String empId, double totalHours) {

        AttendanceStatus status;
        if (totalHours >= 8) {
            status = AttendanceStatus.PRESENT;
        } else if (totalHours >= 4) {
            status = AttendanceStatus.HALF_DAY;
        } else {
            status = AttendanceStatus.ABSENT;
        }

        AttendanceResult attendanceResult = new AttendanceResult();
        attendanceResult.setEmpId(empId);
        attendanceResult.setTotalHours(totalHours);
        attendanceResult.setStatus(status);
        return attendanceResult;
    }

    private List<Swipe> getSwipesByCreatedAt() {
        LocalDate date = LocalDate.now();
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);

        return swipeRepository.findBySwipeTimeBetweenOrderBySwipeTime(start, end);
    }
}
