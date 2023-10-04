package com.example.backend.responsemodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {
    private String message;
    private Integer status;
    private List<?> details;
    private Integer dataCount;
}
