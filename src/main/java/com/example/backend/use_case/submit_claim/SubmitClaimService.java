package com.example.backend.use_case.submit_claim;

import com.example.backend.responsemodel.CommonResponse;

public interface SubmitClaimService {
    CommonResponse<String> submitClaim(String claimNumber);
}
