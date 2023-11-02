package com.example.backend.requestmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BasicClaimModel {
    private String claimNumber;
    private String status;
    private String type;
    private String dateCreated;
    private String policyNumber;
}
