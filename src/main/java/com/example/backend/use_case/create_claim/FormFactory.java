package com.example.backend.use_case.create_claim;

import com.example.backend.entities.CertificationOfDeathForm;
import com.example.backend.entities.Form;
import com.example.backend.entities.LendersStatementForm;
import com.example.backend.entities.LifeClaimInformationRequestForm;
import com.example.backend.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FormFactory {
    @Autowired
    FormRepository<LifeClaimInformationRequestForm> lifeClaimInformationRequestFormRepository;

    @Autowired
    FormRepository<CertificationOfDeathForm> certificationOfDeathFormRepository;

    @Autowired
    FormRepository<LendersStatementForm> lendersStatementFormRepository;

    public Form createLifeClaimInformationRequestForm() {
        LifeClaimInformationRequestForm lifeClaimInformationRequestForm = new LifeClaimInformationRequestForm();
        lifeClaimInformationRequestForm.setFormType("Life Claim Information Request Form");
        lifeClaimInformationRequestForm.setLastModified(LocalDate.now());
        lifeClaimInformationRequestFormRepository.save(lifeClaimInformationRequestForm);
        return lifeClaimInformationRequestForm;
    }

    public Form createCertificationOfDeathForm() {
        CertificationOfDeathForm certificationOfDeathForm = new CertificationOfDeathForm();
        certificationOfDeathForm.setFormType("Certification of Death Form");
        certificationOfDeathForm.setLastModified(LocalDate.now());
        certificationOfDeathFormRepository.save(certificationOfDeathForm);
        return certificationOfDeathForm;
    }

    public Form createLendersStatementForm() {
        LendersStatementForm lendersStatementForm = new LendersStatementForm();
        lendersStatementForm.setFormType("Lender's Statement Form");
        lendersStatementForm.setLastModified(LocalDate.now());
        lendersStatementFormRepository.save(lendersStatementForm);
        return lendersStatementForm;
    }
}
