package com.ali.swipeservice.event;

import com.ali.swipeservice.model.constant.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Azam
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwipeTotalHoursEvent {
    private String empId;
    private double totalHours;
    private AttendanceStatus status;
}
