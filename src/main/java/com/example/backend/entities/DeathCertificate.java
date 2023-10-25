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
public class DeathCertificate implements Document {
    private String name;
    private String dateOfBirth;
    private String dateOfDeath;
    private String placeOfDeath;
    private String causeOfDeath;
    private String physicianName;

    private String documentType = "Document";

    public String[] getAllData() {
        String[] deathCertificate = new String[6];
        deathCertificate[0] = name;
        deathCertificate[1] = dateOfBirth;
        deathCertificate[2] = dateOfDeath;
        deathCertificate[3] = placeOfDeath;
        deathCertificate[4] = causeOfDeath;
        deathCertificate[5] = physicianName;
        return deathCertificate;
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