package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormTest {

    private ConcreteForm concreteForm;

    @BeforeEach
    void setUp() {
        concreteForm = new ConcreteForm();
    }

    @Test
    void testFormFields() {
        // Mock data
        Long id = 1L;
        String formType = "ConcreteForm";
        String formName = "Form1";
        LocalDate lastModified = LocalDate.of(2023, 1, 1);
        String fileUrl = "http://example.com/form1";

        // Set values using setters
        concreteForm.setId(id);
        concreteForm.setFormType(formType);
        concreteForm.setFormName(formName);
        concreteForm.setLastModified(lastModified);
        concreteForm.setFileUrl(fileUrl);

        // Verify the values using getters
        assertEquals(id, concreteForm.getId());
        assertEquals(formType, concreteForm.getFormType());
        assertEquals(formName, concreteForm.getFormName());
        assertEquals(lastModified, concreteForm.getLastModified());
        assertEquals(fileUrl, concreteForm.getFileUrl());
    }

    // Concrete implementation of the abstract Form class for testing
    private static class ConcreteForm extends Form {
        // No additional code is needed for this test
    }
}