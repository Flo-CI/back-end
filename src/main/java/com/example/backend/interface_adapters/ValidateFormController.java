package com.example.backend.interface_adapters;

import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.FormError;
import com.example.backend.use_case.validate_form.ValidateFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ValidateFormController {
    @Autowired
    private ValidateFormService validateFormService;

    @PostMapping("/validate")
    public CommonListResponse<FormError> validateForm(@RequestParam(name = "claimNumber") String claimNumber,
            @RequestParam(name = "type") String type) {
        return validateFormService.validateForm(claimNumber, type);
    }
}
