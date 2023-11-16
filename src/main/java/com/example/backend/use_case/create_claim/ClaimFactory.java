package com.example.backend.use_case.create_claim;

import com.example.backend.entities.Claim;
import com.example.backend.entities.Document;
import com.example.backend.entities.Form;
import com.example.backend.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

import java.time.LocalDate;

@Component
public class ClaimFactory {
    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private FormFactory formFactory;

    public Claim createLossOfLifeClaim(String policyNumber) {
        Claim lossOfLifeClaim = new Claim();
        Random random = new Random();
        String claimNumber = Integer.toString(random.nextInt(1000000, 9999999));
        lossOfLifeClaim.setClaimNumber(claimNumber);
        lossOfLifeClaim.setStatus("In Progress");
        lossOfLifeClaim.setType("Loss of Life Claim");
        lossOfLifeClaim.setDateCreated(LocalDate.now());
        Document deathCertificate = documentFactory.createDocument("Death Certificate");
        Document statementOfAccount = documentFactory.createDocument("Statement of Account");
        lossOfLifeClaim.addDocument(deathCertificate);
        lossOfLifeClaim.addDocument(statementOfAccount);
        Form lifeClaimInformationRequestForm = formFactory.createLifeClaimInformationRequestForm();
        Form certificationOfDeathForm = formFactory.createCertificationOfDeathForm();
        Form lendersStatementForm = formFactory.createLendersStatementForm();
        lossOfLifeClaim.addForm(lifeClaimInformationRequestForm);
        lossOfLifeClaim.addForm(certificationOfDeathForm);
        lossOfLifeClaim.addForm(lendersStatementForm);
        claimRepository.save(lossOfLifeClaim);
        return lossOfLifeClaim;
    }
}
