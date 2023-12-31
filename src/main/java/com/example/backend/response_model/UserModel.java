package com.example.backend.response_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserModel {
    private String policyNumber;
    private List<ClaimBaseModel> claims;
}
