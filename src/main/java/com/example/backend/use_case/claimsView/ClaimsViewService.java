package com.example.backend.use_case.claimsView;

import com.example.backend.responsemodel.ClaimBaseModel;
import com.example.backend.responsemodel.CommonListResponse;
import com.example.backend.responsemodel.CommonResponse;
import com.example.backend.responsemodel.UserModel;

public interface ClaimsViewService {
    CommonListResponse<ClaimBaseModel> getClaims(String policyNumber);
}
