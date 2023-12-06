package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LifeClaimInformationRequestFormTest {

    private LifeClaimInformationRequestForm lifeClaimForm;

    @BeforeEach
    void setUp() {
        lifeClaimForm = new LifeClaimInformationRequestForm();
    }

    @Test
    void testLifeClaimFormFields() {
        // Mock data
        Long id = 1L;
        String formType = "LifeClaimInformationRequestForm";
        String formName = "Form1";
        Boolean completedPhysicianForm = true;
        Boolean submittedDeathCertificate = false;
        Boolean completedLenderStatement = true;
        String deceasedName = "John Doe";
        LocalDate deceasedDateOfBirth = LocalDate.of(1980, 1, 1);
        LocalDate deceasedDateOfDeath = LocalDate.of(2023, 1, 15);
        String causeOfDeath = "Natural Causes";
        Boolean wasHospitalized = true;
        String hospitalName = "City Hospital";
        LocalDate hospitalAdmissionDate = LocalDate.of(2023, 1, 10);
        String hospitalAddress = "123 Main St, City";
        String attendingPhysicianName = "Dr. Smith";
        String hospitalPhoneNumber = "555-1234";
        String familyPhysicianName = "Dr. Johnson";
        String familyPhysicianAddress = "456 Oak St, City";
        String familyPhysicianPhoneNumber = "555-5678";
        String occupation = "Software Engineer";
        LocalDate dateLastWorked = LocalDate.of(2023, 1, 5);
        String employerName = "Tech Company";
        String reasonForNotWorking = "Medical Leave";
        String employerAddress = "789 Tech St, City";
        String employerPhoneNumber = "555-9876";
        String nameNextOfKin = "Jane Doe";
        String relationshipNextOfKin = "Spouse";
        String addressNextOfKin = "789 Maple St, City";
        String phoneNumberNextOfKin = "555-5432";
        LocalDate dateSigned = LocalDate.now();

        // Set values using setters
        lifeClaimForm.setId(id);
        lifeClaimForm.setFormType(formType);
        lifeClaimForm.setFormName(formName);
        lifeClaimForm.setCompletedPhysicianForm(completedPhysicianForm);
        lifeClaimForm.setSubmittedDeathCertificate(submittedDeathCertificate);
        lifeClaimForm.setCompletedLenderStatement(completedLenderStatement);
        lifeClaimForm.setDeceasedName(deceasedName);
        lifeClaimForm.setDeceasedDateOfBirth(deceasedDateOfBirth);
        lifeClaimForm.setDeceasedDateOfDeath(deceasedDateOfDeath);
        lifeClaimForm.setCauseOfDeath(causeOfDeath);
        lifeClaimForm.setWasHospitalized(wasHospitalized);
        lifeClaimForm.setHospitalName(hospitalName);
        lifeClaimForm.setHospitalAdmissionDate(hospitalAdmissionDate);
        lifeClaimForm.setHospitalAddress(hospitalAddress);
        lifeClaimForm.setAttendingPhysicianName(attendingPhysicianName);
        lifeClaimForm.setHospitalPhoneNumber(hospitalPhoneNumber);
        lifeClaimForm.setFamilyPhysicianName(familyPhysicianName);
        lifeClaimForm.setFamilyPhysicianAddress(familyPhysicianAddress);
        lifeClaimForm.setFamilyPhysicianPhoneNumber(familyPhysicianPhoneNumber);
        lifeClaimForm.setOccupation(occupation);
        lifeClaimForm.setDateLastWorked(dateLastWorked);
        lifeClaimForm.setEmployerName(employerName);
        lifeClaimForm.setReasonForNotWorking(reasonForNotWorking);
        lifeClaimForm.setEmployerAddress(employerAddress);
        lifeClaimForm.setEmployerPhoneNumber(employerPhoneNumber);
        lifeClaimForm.setNameNextOfKin(nameNextOfKin);
        lifeClaimForm.setRelationshipNextOfKin(relationshipNextOfKin);
        lifeClaimForm.setAddressNextOfKin(addressNextOfKin);
        lifeClaimForm.setPhoneNumberNextOfKin(phoneNumberNextOfKin);
        lifeClaimForm.setDateSigned(dateSigned);

        // Verify the values using getters
        assertEquals(id, lifeClaimForm.getId());
        assertEquals(formType, lifeClaimForm.getFormType());
        assertEquals(formName, lifeClaimForm.getFormName());
        assertEquals(completedPhysicianForm, lifeClaimForm.getCompletedPhysicianForm());
        assertEquals(submittedDeathCertificate, lifeClaimForm.getSubmittedDeathCertificate());
        assertEquals(completedLenderStatement, lifeClaimForm.getCompletedLenderStatement());
        assertEquals(deceasedName, lifeClaimForm.getDeceasedName());
        assertEquals(deceasedDateOfBirth, lifeClaimForm.getDeceasedDateOfBirth());
        assertEquals(deceasedDateOfDeath, lifeClaimForm.getDeceasedDateOfDeath());
        assertEquals(causeOfDeath, lifeClaimForm.getCauseOfDeath());
        assertEquals(wasHospitalized, lifeClaimForm.getWasHospitalized());
        assertEquals(hospitalName, lifeClaimForm.getHospitalName());
        assertEquals(hospitalAdmissionDate, lifeClaimForm.getHospitalAdmissionDate());
        assertEquals(hospitalAddress, lifeClaimForm.getHospitalAddress());
        assertEquals(attendingPhysicianName, lifeClaimForm.getAttendingPhysicianName());
        assertEquals(hospitalPhoneNumber, lifeClaimForm.getHospitalPhoneNumber());
        assertEquals(familyPhysicianName, lifeClaimForm.getFamilyPhysicianName());
        assertEquals(familyPhysicianAddress, lifeClaimForm.getFamilyPhysicianAddress());
        assertEquals(familyPhysicianPhoneNumber, lifeClaimForm.getFamilyPhysicianPhoneNumber());
        assertEquals(occupation, lifeClaimForm.getOccupation());
        assertEquals(dateLastWorked, lifeClaimForm.getDateLastWorked());
        assertEquals(employerName, lifeClaimForm.getEmployerName());
        assertEquals(reasonForNotWorking, lifeClaimForm.getReasonForNotWorking());
        assertEquals(employerAddress, lifeClaimForm.getEmployerAddress());
        assertEquals(employerPhoneNumber, lifeClaimForm.getEmployerPhoneNumber());
        assertEquals(nameNextOfKin, lifeClaimForm.getNameNextOfKin());
        assertEquals(relationshipNextOfKin, lifeClaimForm.getRelationshipNextOfKin());
        assertEquals(addressNextOfKin, lifeClaimForm.getAddressNextOfKin());
        assertEquals(phoneNumberNextOfKin, lifeClaimForm.getPhoneNumberNextOfKin());
        assertEquals(dateSigned, lifeClaimForm.getDateSigned());
    }
}
