package com.example.backend.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimInitiationForm implements Document {
    private String policyNumber;
    private String claimNumber;
    private String claimType;
    private String claimantName;
    private String claimantAddress;
    private String claimantContactNumber;
    private String claimantEmail;

    // TODO: not sure if we need bank information
    private String claimantBankAccountNumber;
    private String claimantBankName;
    private String claimantBankBranch;
    private String claimantBankCity;
    private String claimantBankState;
    private String claimantBankCountry;
    private String claimantBankAddress;
    private String claimantBankContactNumber;
    private String claimantBankEmail;

    private String claimantRelationshipToInsured;
    private String insuredName;
    private String insuredAddress;
    private String insuredContactNumber;
    private String insuredEmail;
    private String insuredDateOfBirth;
    private String reason;

    private String documentType = "Form";

    @Override
    public String[] getAllData() {
        String[] claimInitiationForm = new String[22];
        claimInitiationForm[0] = policyNumber;
        claimInitiationForm[1] = claimNumber;
        claimInitiationForm[2] = claimType;
        claimInitiationForm[3] = claimantName;
        claimInitiationForm[4] = claimantAddress;
        claimInitiationForm[5] = claimantContactNumber;
        claimInitiationForm[6] = claimantEmail;
        claimInitiationForm[7] = claimantBankAccountNumber;
        claimInitiationForm[8] = claimantBankName;
        claimInitiationForm[9] = claimantBankBranch;
        claimInitiationForm[10] = claimantBankCity;
        claimInitiationForm[11] = claimantBankState;
        claimInitiationForm[12] = claimantBankCountry;
        claimInitiationForm[13] = claimantBankAddress;
        claimInitiationForm[14] = claimantBankContactNumber;
        claimInitiationForm[15] = claimantBankEmail;
        claimInitiationForm[16] = claimantRelationshipToInsured;
        claimInitiationForm[17] = insuredName;
        claimInitiationForm[18] = insuredAddress;
        claimInitiationForm[19] = insuredContactNumber;
        claimInitiationForm[20] = insuredEmail;
        claimInitiationForm[21] = insuredDateOfBirth;
        claimInitiationForm[22] = reason;
        return claimInitiationForm;
    }

    @Override
    public String getDocumentType() {
        return documentType;
    }

    @Override
    public boolean validateDocument() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateDocument'");
    }

}
