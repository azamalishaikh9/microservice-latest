package com.ali.swipeservice;

import com.ali.swipeservice.controller.SwipeController;
import com.ali.swipeservice.model.dto.request.SwipeRequest;
import com.ali.swipeservice.model.dto.response.AttendanceResult;
import com.ali.swipeservice.model.dto.response.CommonResponse;
import com.ali.swipeservice.model.dto.response.SwipeResponse;
import com.ali.swipeservice.service.SwipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SwipeServiceApplicationTests {

	@Mock
	private SwipeService swipeService;

	@InjectMocks
	private SwipeController swipeController;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		objectMapper = new ObjectMapper();
	}

	@Test
	public void saveSwipeRequest_ShouldReturnSuccessResponse() {
		SwipeRequest swipeRequest = new SwipeRequest();
		SwipeResponse swipeResponse = new SwipeResponse();

		when(swipeService.saveSwipeDetails(any(SwipeRequest.class))).thenReturn(swipeResponse);

		CommonResponse responseEntity = swipeController.saveSwipeRequest(swipeRequest);

		verify(swipeService).saveSwipeDetails(swipeRequest);
		assert responseEntity.getMessage().equals("Swipe details saved successfully.");
	}

	@Test
	public void calculateTotalHours_ShouldReturnSuccessResponse() {
		HashMap<String, AttendanceResult> attendanceResult = new HashMap<>();

		when(swipeService.calculateTotalHours()).thenReturn(attendanceResult);

		CommonResponse responseEntity = swipeController.calculateTotalHours();

		verify(swipeService).calculateTotalHours();
		assert responseEntity.getMessage().equals("All employees total hours counted");
	}

}
