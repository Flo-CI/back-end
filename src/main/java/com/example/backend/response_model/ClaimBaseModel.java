package com.example.backend.response_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClaimBaseModel {
    private String claimNumber;
    private String status;
    private String type;
    private LocalDate dateCreated;
}
