package com.example.backend.interface_adapters;

import com.example.backend.response_model.CommonResponse;
import com.example.backend.use_case.submit_claim.SubmitClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmitClaimController {
    @Autowired
    private SubmitClaimService submitClaimService;

    @PostMapping("/claim/submit")
    public CommonResponse<String> submitClaim(String claimNumber) {
        return submitClaimService.submitClaim(claimNumber);
    }

}
