package com.example.backend.response_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormError {
    private String field;
    private String message;
}
