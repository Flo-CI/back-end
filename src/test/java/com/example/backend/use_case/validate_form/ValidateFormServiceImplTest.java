package com.example.backend.use_case.validate_form;

import com.example.backend.entities.CertificationOfDeathForm;
import com.example.backend.entities.Claim;
import com.example.backend.entities.Form;
import com.example.backend.entities.LifeClaimInformationRequestForm;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.FormRepository;
import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.FormError;
import com.example.backend.response_model.GenericException;
import com.example.backend.use_case.validate_form.form_validator.CertificationOfDeathFormValidator;
import com.example.backend.use_case.validate_form.form_validator.FormValidator;
import com.example.backend.use_case.validate_form.ocr_service.OcrForFormServiceImpl;
import com.example.backend.use_case.validate_form.ocr_service.OcrFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidateFormServiceImplTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private FormRepository<CertificationOfDeathForm> certificationOfDeathFormRepository;

    @InjectMocks
    private ValidateFormServiceImpl validateFormService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testValidateFormClaimNotFound() {
        // Mock data
        String claimNumber = "123";
        String formType = "Certification of Death Form";

        // Stubbing repository method
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(null);

        // Perform the action and assert exception
        assertThrows(GenericException.class, () -> validateFormService.validateForm(claimNumber, formType),
                "Claim not found");

        // Verify interactions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(certificationOfDeathFormRepository, never()).findById(anyLong());
    }

    @Test
    void testValidateFormFormNotFound() {
        // Mock data
        String claimNumber = "123";
        String formType = "Certification of Death Form";

        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);
        Form form = new CertificationOfDeathForm();
        form.setFormType("Certification of Death Form");
        form.setId(1L);
        claim.setForms(List.of(form));

        // Stubbing repository methods
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(claim);
        when(certificationOfDeathFormRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Perform the action and assert exception
        assertThrows(GenericException.class, () -> validateFormService.validateForm(claimNumber, formType),
                "Form not found");

        // Verify interactions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(certificationOfDeathFormRepository, times(1)).findById(anyLong());
    }

    // Add more test cases as needed
}
