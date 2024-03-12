package com.ali.swipeservice.model.dto.response;

import com.ali.swipeservice.model.constant.AttendanceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Azam
 */

@Getter
@Setter
@NoArgsConstructor
public class AttendanceResult {

    private String empId;
    private double totalHours;
    private AttendanceStatus status;

}
