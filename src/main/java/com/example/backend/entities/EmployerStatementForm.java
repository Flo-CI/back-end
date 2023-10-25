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
public class EmployerStatementForm implements Document {
    private String name;
    private String dateOfBirth;
    private String employerName;
    private String employerAddress;
    private String employerPhone;
    private String employerEmail;
    private String employerContactPerson;
    private String employerContactPersonPhone;
    private String employerContactPersonEmail;
    private String employerContactPersonTitle;
    private String employerContactPersonRelationship;

    private String statement;

    private String documentType = "Form";

    @Override
    public String[] getAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllData'");
    }

    @Override
    public String getDocumentType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDocumentType'");
    }

    @Override
    public boolean validateDocument() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateDocument'");
    }

}
