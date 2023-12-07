package com.example.backend.use_case.validate_form.form_validator;
import com.example.backend.entities.CertificationOfDeathForm;
import com.example.backend.response_model.FormError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CertificationOfDeathFormValidatorTest {

    @Mock
    private CertificationOfDeathForm form;

    @InjectMocks
    private CertificationOfDeathFormValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFillFormUsingOcr() {
        // Mock data
        HashMap<String, List<String>> formMap = new HashMap<>();
        formMap.put("Full name of deceased", List.of("John Doe"));
        formMap.put("Date of death (mo/day/yr)", List.of("01/01/22"));
        formMap.put("Immediate cause of death", List.of("Heart attack"));
        formMap.put("Due to a consequence of", List.of("Cardiac arrest"));
        formMap.put("When did symptoms first appear or accident happen (mo/day/yr)", List.of("12/31/21"));
        formMap.put("Natural", List.of(":selected:"));
        formMap.put("Place of death (if institution or hospital, give name)", List.of("Hospital"));
        formMap.put("Yes", List.of(":selected:"));
        formMap.put("Yes", List.of(":selected:"));
        formMap.put("Name", List.of("Dr. Smith"));
        formMap.put("Title", List.of("Cardiologist"));
        formMap.put("Address (street, city, province, postal code)", List.of("123 Main St, City, State, 12345"));
        formMap.put("Telephone number", List.of("123-456-7890"));
        formMap.put("Date (mo/day/yr)", List.of("01/01/22"));



        // Perform the action
        validator.fillFormUsingOcr(formMap);

        // Verify interactions
        // Note: Verification of setter methods can be done using verify on the mock form object
        // based on the implementation of CertificationOfDeathFormValidator

        // Add more assertions as needed
    }

    @Test
    void testValidateForm() {
        // Mock data
        when(form.getNameDeceased()).thenReturn("John Doe");
        when(form.getDateOfDeath()).thenReturn(LocalDate.parse("2022-01-01"));
        when(form.getImmediateCauseOfDeath()).thenReturn("Heart attack");
        when(form.getDueTo()).thenReturn("Cardiac arrest");
        when(form.getDateOfSymptomsOnset()).thenReturn(LocalDate.parse("2021-12-31"));
        when(form.getCauseOfDeath()).thenReturn("Natural");
        when(form.getPlaceOfDeath()).thenReturn("Hospital");
        when(form.getInquestPerformed()).thenReturn(Boolean.TRUE);
        when(form.getAutopsyPerformed()).thenReturn(Boolean.TRUE);
        when(form.getNameOfPhysician()).thenReturn("Dr. Smith");
        when(form.getTitleOfPhysician()).thenReturn("Cardiologist");
        when(form.getAddressOfPhysician()).thenReturn("123 Main St, City, State, 12345");
        when(form.getPhoneOfPhysician()).thenReturn("123-456-7890");
        when(form.getDateSigned()).thenReturn(LocalDate.parse("2022-01-01"));

        // Perform the action
        List<FormError> errors = validator.validateForm();

        // Verify interactions and assertions
        // Note: Verification of getter methods can be done using verify on the mock form object
        // based on the implementation of CertificationOfDeathFormValidator
        assertEquals(0, errors.size());

        // Add more assertions as needed
    }

    @Test
    void testValidateEmptyForm() {
        // Mock data
        when(form.getNameDeceased()).thenReturn(null);
        when(form.getDateOfDeath()).thenReturn(null);
        when(form.getImmediateCauseOfDeath()).thenReturn(null);
        when(form.getDueTo()).thenReturn(null);
        when(form.getDateOfSymptomsOnset()).thenReturn(null);
        when(form.getCauseOfDeath()).thenReturn(null);
        when(form.getPlaceOfDeath()).thenReturn(null);
        when(form.getInquestPerformed()).thenReturn(null);
        when(form.getAutopsyPerformed()).thenReturn(null);
        when(form.getNameOfPhysician()).thenReturn(null);
        when(form.getTitleOfPhysician()).thenReturn(null);
        when(form.getAddressOfPhysician()).thenReturn(null);
        when(form.getPhoneOfPhysician()).thenReturn(null);
        when(form.getDateSigned()).thenReturn(null);
        when(form.getDueTo()).thenReturn(null);
        when(form.getDateOfSymptomsOnset()).thenReturn(null);
        when(form.getCauseOfDeath()).thenReturn(null);
        when(form.getPlaceOfDeath()).thenReturn(null);
        when(form.getInquestPerformed()).thenReturn(null);
        when(form.getAutopsyPerformed()).thenReturn(null);
        when(form.getNameOfPhysician()).thenReturn(null);
        when(form.getTitleOfPhysician()).thenReturn(null);
        when(form.getAddressOfPhysician()).thenReturn(null);
        when(form.getPhoneOfPhysician()).thenReturn(null);
        when(form.getDateSigned()).thenReturn(null);

        // Perform the action
        List<FormError> errors = validator.validateForm();

        // Verify interactions and assertions
        // Note: Verification of getter methods can be done using verify on the mock form object
        // based on the implementation of CertificationOfDeathFormValidator
        assertEquals(14, errors.size());

        // Add more assertions as needed
    }

    // Add more test cases as needed
}
