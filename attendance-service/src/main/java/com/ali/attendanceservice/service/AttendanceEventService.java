package com.ali.attendanceservice.service;

import com.ali.attendanceservice.event.AttendanceResult;
import com.ali.attendanceservice.model.Attendance;
import com.ali.attendanceservice.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Azam
 */
@Service
@RequiredArgsConstructor
public class AttendanceEventService {

    private final AttendanceRepository attendanceRepository;

    private final KafkaTemplate<String, AttendanceResult> kafkaTemplate;

    @KafkaListener(topics = "attendanceResult")
    public void handleAttendanceResult(HashMap<String, AttendanceResult> attendanceResultHashMap){

        List<Attendance> attendances = new ArrayList<>();

        for(Map.Entry<String, AttendanceResult> attendanceResultMap : attendanceResultHashMap.entrySet()){
            String empId = attendanceResultMap.getKey();
            AttendanceResult attendanceResult = attendanceResultMap.getValue();
            Attendance attendance = new Attendance();
            attendance.setEmpId(empId);
            attendance.setStatus(attendanceResult.getStatus());
            attendance.setTotalHours(attendanceResult.getTotalHours());
            attendance.setCreatedAt(LocalDateTime.now());
            attendances.add(attendance);
        }

        attendanceRepository.saveAll(attendances);

    }
}
