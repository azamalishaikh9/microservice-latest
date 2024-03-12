package com.ali.attendanceservice.model;

import com.ali.attendanceservice.model.constant.AttendanceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Azam
 */

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    private Integer id;

    private String empId;

    private double totalHours;

    private AttendanceStatus status;

    private LocalDateTime createdAt;
}
