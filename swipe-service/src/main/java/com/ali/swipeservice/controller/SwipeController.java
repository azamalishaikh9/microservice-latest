package com.ali.swipeservice.controller;

import com.ali.swipeservice.model.dto.request.SwipeRequest;
import com.ali.swipeservice.model.dto.response.AttendanceResult;
import com.ali.swipeservice.model.dto.response.CommonResponse;
import com.ali.swipeservice.model.dto.response.SwipeResponse;
import com.ali.swipeservice.service.SwipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Azam
 */

@RestController
@RequestMapping("/swipe")
@RequiredArgsConstructor
public class SwipeController {

    private final SwipeService swipeService;

    @PostMapping("/")
    public CommonResponse saveSwipeRequest(@RequestBody SwipeRequest swipeRequest) {

        SwipeResponse swipeResponse = swipeService.saveSwipeDetails(swipeRequest);

        return new CommonResponse(swipeResponse, "Swipe details saved successfully.");
    }

    @GetMapping("total-hours")
    public CommonResponse calculateTotalHours() {

        HashMap<String, AttendanceResult> attendanceResult = swipeService.calculateTotalHours();

        return new CommonResponse(attendanceResult, "All employees total hours counted");
    }

}
