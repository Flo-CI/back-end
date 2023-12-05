package com.example.backend.use_case.claim_rank;

import java.util.HashMap;

import com.example.backend.response_model.CommonResponse;

public interface ClaimRankService {
    public CommonResponse<HashMap<String, Integer>> getClaimRanks(String policyNumber);

}
