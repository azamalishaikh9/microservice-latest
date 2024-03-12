package com.ali.attendanceservice.event;

import com.ali.attendanceservice.model.constant.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Azam
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResult {
    private String empId;
    private double totalHours;
    private AttendanceStatus status;
}
