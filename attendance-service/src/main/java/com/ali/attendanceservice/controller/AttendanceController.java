package com.ali.attendanceservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Azam
 */

@RestController
@RequestMapping("/attendance/")
public class AttendanceController {

    @GetMapping("working-hour")
    public String totalWorkingHour(){
        return "total working hours";
    }

}
