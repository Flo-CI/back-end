package com.example.backend.use_case.claims_view;

import com.example.backend.response_model.ClaimBaseModel;
import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.UserModel;

public interface ClaimsViewService {
    CommonListResponse<ClaimBaseModel> getClaims(String policyNumber);
}
