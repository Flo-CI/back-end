package com.example.backend.interface_adapters;

import com.example.backend.responsemodel.ClaimBaseModel;
import com.example.backend.responsemodel.CommonListResponse;
import com.example.backend.use_case.claims_view.ClaimsViewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClaimsViewController {

    @Autowired
    private ClaimsViewService claimsViewService;

    @GetMapping("/claims")
    public CommonListResponse<ClaimBaseModel> getClaims(@RequestParam String policyNumber) {
        return claimsViewService.getClaims(policyNumber);
    }
}
