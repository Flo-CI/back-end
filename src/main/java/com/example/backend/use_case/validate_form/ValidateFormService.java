package com.example.backend.use_case.validate_form;

import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.FormError;

public interface ValidateFormService {
    CommonListResponse<FormError> validateForm(String claimNumber, String type);
}
