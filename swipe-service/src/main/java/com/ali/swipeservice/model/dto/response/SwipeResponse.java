package com.ali.swipeservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Azam
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SwipeResponse {

    private String empId;
    private LocalDateTime swipeTime;
}
