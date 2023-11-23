package com.example.backend.use_case.validate_form.form_validator;

import com.example.backend.responsemodel.FormError;

import java.util.HashMap;
import java.util.List;

public interface FormValidator {
    void fillFormUsingOcr(HashMap<String, List<String>> formMap);
    List<FormError> validateForm();
    String getFormUrl();
}
