package com.ali.swipeservice.model;

import com.ali.swipeservice.model.constant.SwipeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Azam
 */

@Document(collection = "swipe-details")
@Getter
@Setter
@NoArgsConstructor
public class Swipe {

    @Id
    private String id;

    private String empId;

    private LocalDateTime swipeTime;

    private SwipeType swipeType;

    private LocalDateTime createdAt;
}
