package com.ali.swipeservice.model.dto.response;

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
public class CommonResponse {

    private Object data;
    private String message;

}
