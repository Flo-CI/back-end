package com.example.backend.response_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommonListResponse<T> {
    private String message;
    private Integer status;
    private List<T> details;
    private Integer dataCount;
}
