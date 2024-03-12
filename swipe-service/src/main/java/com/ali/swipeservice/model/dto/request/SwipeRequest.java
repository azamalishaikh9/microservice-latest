package com.ali.swipeservice.model.dto.request;

import com.ali.swipeservice.model.constant.SwipeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Azam
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SwipeRequest {

    private String empId;
    private SwipeType swipeType;
}
