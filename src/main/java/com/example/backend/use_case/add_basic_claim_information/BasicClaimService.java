package com.example.backend.use_case.add_basic_claim_information;

import com.example.backend.request_model.BasicClaimModel;
import com.example.backend.response_model.CommonResponse;

public interface BasicClaimService {
    public CommonResponse<String> addBasicClaimInformation(BasicClaimModel basicClaimModel);
}
