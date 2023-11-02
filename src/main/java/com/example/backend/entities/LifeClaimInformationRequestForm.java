package com.example.backend.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LifeClaimInformationRequestForm extends Form {
    private Boolean completedPhysicianForm;
    private Boolean submittedDeathCertificate;
    private Boolean completedLenderStatement;
    private String deceasedName;
    private LocalDate deceasedDateOfBirth;
    private LocalDate deceasedDateOfDeath;
    private String causeOfDeath;
    private Boolean wasHospitalized;
    private String hospitalName;
    private LocalDate hospitalAdmissionDate;
    private String hospitalAddress;
    private String attendingPhysicianName;
    private String hospitalPhoneNumber;
    private String familyPhysicianName;
    private String familyPhysicianAddress;
    private String familyPhysicianPhoneNumber;
    private String occupation;
    private LocalDate dateLastWorked;
    private String employerName;
    private String reasonForNotWorking;
    private String employerAddress;
    private String employerPhoneNumber;
    private String nameNextOfKin;
    private String relationshipNextOfKin;
    private String addressNextOfKin;
    private String phoneNumberNextOfKin;
    private LocalDate dateSigned;
}
