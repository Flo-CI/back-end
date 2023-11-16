package com.example.backend.use_case.create_claim;


import com.example.backend.responsemodel.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreateClaimService {
    public CommonResponse<String> createClaim(String policyNumber, String type);
}
