package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertificationOfDeathFormTest {

    private CertificationOfDeathForm certificationOfDeathForm;

    @BeforeEach
    void setUp() {
        certificationOfDeathForm = new CertificationOfDeathForm();
    }

    @Test
    void testCertificationOfDeathFormFields() {
        // Mock data
        String nameDeceased = "John Doe";
        LocalDate dateOfDeath = LocalDate.of(2023, 1, 1);
        String immediateCauseOfDeath = "Heart failure";
        String dueTo = "Coronary artery disease";
        LocalDate dateOfSymptomsOnset = LocalDate.of(2022, 12, 25);
        String causeOfDeath = "Natural causes";
        String placeOfDeath = "Hospital";
        Boolean inquestPerformed = false;
        Boolean autopsyPerformed = true;
        String nameOfPhysician = "Dr. Smith";
        String titleOfPhysician = "Cardiologist";
        String addressOfPhysician = "123 Main St";
        String phoneOfPhysician = "555-1234";
        LocalDate dateSigned = LocalDate.now();

        // Set values using setters
        certificationOfDeathForm.setNameDeceased(nameDeceased);
        certificationOfDeathForm.setDateOfDeath(dateOfDeath);
        certificationOfDeathForm.setImmediateCauseOfDeath(immediateCauseOfDeath);
        certificationOfDeathForm.setDueTo(dueTo);
        certificationOfDeathForm.setDateOfSymptomsOnset(dateOfSymptomsOnset);
        certificationOfDeathForm.setCauseOfDeath(causeOfDeath);
        certificationOfDeathForm.setPlaceOfDeath(placeOfDeath);
        certificationOfDeathForm.setInquestPerformed(inquestPerformed);
        certificationOfDeathForm.setAutopsyPerformed(autopsyPerformed);
        certificationOfDeathForm.setNameOfPhysician(nameOfPhysician);
        certificationOfDeathForm.setTitleOfPhysician(titleOfPhysician);
        certificationOfDeathForm.setAddressOfPhysician(addressOfPhysician);
        certificationOfDeathForm.setPhoneOfPhysician(phoneOfPhysician);
        certificationOfDeathForm.setDateSigned(dateSigned);

        // Verify the values
        assertEquals(nameDeceased, certificationOfDeathForm.getNameDeceased());
        assertEquals(dateOfDeath, certificationOfDeathForm.getDateOfDeath());
        assertEquals(immediateCauseOfDeath, certificationOfDeathForm.getImmediateCauseOfDeath());
        assertEquals(dueTo, certificationOfDeathForm.getDueTo());
        assertEquals(dateOfSymptomsOnset, certificationOfDeathForm.getDateOfSymptomsOnset());
        assertEquals(causeOfDeath, certificationOfDeathForm.getCauseOfDeath());
        assertEquals(placeOfDeath, certificationOfDeathForm.getPlaceOfDeath());
        assertEquals(inquestPerformed, certificationOfDeathForm.getInquestPerformed());
        assertEquals(autopsyPerformed, certificationOfDeathForm.getAutopsyPerformed());
        assertEquals(nameOfPhysician, certificationOfDeathForm.getNameOfPhysician());
        assertEquals(titleOfPhysician, certificationOfDeathForm.getTitleOfPhysician());
        assertEquals(addressOfPhysician, certificationOfDeathForm.getAddressOfPhysician());
        assertEquals(phoneOfPhysician, certificationOfDeathForm.getPhoneOfPhysician());
        assertEquals(dateSigned, certificationOfDeathForm.getDateSigned());
    }
}