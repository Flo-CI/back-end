package com.example.backend.use_case.add_basic_claim_information;

import com.example.backend.requestmodel.BasicClaimModel;
import com.example.backend.responsemodel.CommonResponse;

public interface BasicClaimService {
    public CommonResponse<String> addBasicClaimInformation(BasicClaimModel basicClaimModel);
}
