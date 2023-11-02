package com.example.backend.use_case.addBasicClaimInformation;

import com.example.backend.requestmodel.BasicClaimModel;
import com.example.backend.responsemodel.CommonResponse;
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