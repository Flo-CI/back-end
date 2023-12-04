package com.example.backend;

import com.example.backend.entities.Claim;
import com.example.backend.response_model.ClaimBaseModel;

public final class Utils {
    public static ClaimBaseModel convertToClaimBaseModel(Claim claim) {
        ClaimBaseModel claimBaseModel = new ClaimBaseModel();
        claimBaseModel.setClaimNumber(claim.getClaimNumber());
        claimBaseModel.setStatus(claim.getStatus());
        claimBaseModel.setType(claim.getType());
        claimBaseModel.setDateCreated(claim.getDateCreated());
        return claimBaseModel;
    }
}
