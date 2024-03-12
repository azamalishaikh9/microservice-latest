package com.ali.attendanceservice.repository;

import com.ali.attendanceservice.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Azam
 */

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
