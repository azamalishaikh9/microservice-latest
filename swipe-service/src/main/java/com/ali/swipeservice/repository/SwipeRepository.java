package com.ali.swipeservice.repository;

import com.ali.swipeservice.model.Swipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Azam
 */

@Repository
public interface SwipeRepository extends MongoRepository<Swipe, String> {

    long countByEmpId(String empId);

    List<Swipe> findBySwipeTimeBetweenOrderBySwipeTime(LocalDateTime start, LocalDateTime end);

    List<Swipe> findByEmpIdAndSwipeTimeBetweenOrderBySwipeTime(String empId, LocalDateTime start, LocalDateTime end);

    List<Swipe> findByEmpIdOrderBySwipeTime(String empId);
}
