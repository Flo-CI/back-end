package com.example.backend.interface_adapters;

import com.example.backend.request_model.BasicClaimModel;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.use_case.add_basic_claim_information.BasicClaimService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BasicClaimController {

    @Autowired
    private BasicClaimService basicClaimService;

    @PostMapping("claim/basic")
    public CommonResponse<String> addBasicClaimInformation(@RequestBody BasicClaimModel basicClaimModel) {
        return basicClaimService.addBasicClaimInformation(basicClaimModel);
    }
}
