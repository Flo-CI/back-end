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
public class ProofOfAddress implements Document {
    private String name;
    private String address;
    private String country;
    private String documentType = "Document";

    @Override
    public String[] getAllData() {
        String[] proofOfAddress = new String[3];
        proofOfAddress[0] = name;
        proofOfAddress[1] = address;
        proofOfAddress[2] = country;
        return proofOfAddress;
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
