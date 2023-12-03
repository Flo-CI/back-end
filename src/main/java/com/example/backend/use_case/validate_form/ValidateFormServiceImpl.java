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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidateFormServiceImpl implements ValidateFormService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private FormRepository<CertificationOfDeathForm> certificationOfDeathFormRepository;

    @Autowired
    private FormRepository<LifeClaimInformationRequestForm> lifeClaimInformationRequestFormRepository;

    @Override
    public CommonListResponse<FormError> validateForm(String claimNumber, String type) {
        CommonListResponse<FormError> response = new CommonListResponse<>();
        Claim claim = claimRepository.findByClaimNumber(claimNumber);
        if (claim == null) {
            throw new GenericException("Claim not found");
        }
        Long desiredFormId = null;
        for (Form form : claim.getForms()) {
            if (form.getFormType().equals(type)) {
                desiredFormId = form.getId();
            }
        }
        if (desiredFormId == null) {
            throw new GenericException("Form not found");
        }
        if (type.equals("Certification of Death Form")) {
            Optional<CertificationOfDeathForm> desiredFormOpt = certificationOfDeathFormRepository
                    .findById(desiredFormId);
            if (desiredFormOpt.isEmpty()) {
                throw new GenericException("Form not found");
            }
            CertificationOfDeathForm desiredForm = desiredFormOpt.get();
            FormValidator formValidator = new CertificationOfDeathFormValidator(desiredForm);
            OcrFormService ocrFormService = new OcrForFormServiceImpl(formValidator);
            ocrFormService.fillFormUsingOcr();
            certificationOfDeathFormRepository.save(desiredForm);
            response.setDetails(formValidator.validateForm());
            response.setDataCount(response.getDetails().size());
        } else if (type.equals("Life Claim Information Request Form")) {
            return null;
        }
        response.setStatus(200);
        response.setMessage("Form validated " + response.getDataCount() + " errors found");
        return response;
    }
}
