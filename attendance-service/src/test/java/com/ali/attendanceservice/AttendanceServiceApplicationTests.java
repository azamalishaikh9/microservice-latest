package com.ali.attendanceservice;

import com.ali.attendanceservice.event.AttendanceResult;
import com.ali.attendanceservice.model.constant.AttendanceStatus;
import com.ali.attendanceservice.repository.AttendanceRepository;
import com.ali.attendanceservice.service.AttendanceEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AttendanceServiceApplicationTests {

	@Mock
	private AttendanceRepository attendanceRepository;

	@Mock
	private KafkaTemplate<String, AttendanceResult> kafkaTemplate;

	@InjectMocks
	private AttendanceEventService attendanceEventService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void handleAttendanceResult_ShouldSaveAttendances() {
		HashMap<String, AttendanceResult> attendanceResultHashMap = new HashMap<>();
		AttendanceResult attendanceResult = new AttendanceResult();
		attendanceResult.setStatus(AttendanceStatus.PRESENT);
		attendanceResult.setTotalHours(8.0);
		attendanceResultHashMap.put("emp123", attendanceResult);

		attendanceEventService.handleAttendanceResult(attendanceResultHashMap);

		verify(attendanceRepository).saveAll(any(List.class));
	}

	@Test
	void handleAttendanceResult_ShouldSendToKafkaTopic() {
		// Arrange
		HashMap<String, AttendanceResult> attendanceResultHashMap = new HashMap<>();
		AttendanceResult attendanceResult = new AttendanceResult();
		attendanceResult.setStatus(AttendanceStatus.PRESENT);
		attendanceResult.setTotalHours(8.0);
		attendanceResultHashMap.put("emp123", attendanceResult);

		// Act
		attendanceEventService.handleAttendanceResult(attendanceResultHashMap);

		// Assert
		verify(kafkaTemplate).send("attendanceTopic", "emp123", attendanceResult);
	}

}
