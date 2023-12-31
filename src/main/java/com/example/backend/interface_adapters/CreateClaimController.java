package com.example.backend.interface_adapters;

import com.example.backend.response_model.CommonResponse;
import com.example.backend.use_case.create_claim.CreateClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CreateClaimController {

    @Autowired
    CreateClaimService createClaimService;

    @PostMapping("/claim/create")
    public CommonResponse<String> createClaim(@RequestParam String policyNumber, @RequestParam String type) {
        return createClaimService.createClaim(policyNumber, type);
    }
}
