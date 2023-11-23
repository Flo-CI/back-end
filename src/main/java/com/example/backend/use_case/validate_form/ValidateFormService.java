package com.example.backend.use_case.validate_form;

import com.example.backend.responsemodel.CommonListResponse;
import com.example.backend.responsemodel.FormError;

public interface ValidateFormService {
    CommonListResponse<FormError> validateForm(String claimNumber, String type);
}
