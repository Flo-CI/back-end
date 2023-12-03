package com.example.backend.response_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse<T> {
    private String message;
    private Integer status;
    private T details;
}
