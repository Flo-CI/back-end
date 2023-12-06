package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LendersStatementFormTest {

    private LendersStatementForm lendersStatementForm;

    @BeforeEach
    void setUp() {
        lendersStatementForm = new LendersStatementForm();
    }

    @Test
    void testLendersStatementFormFields() {
        // Mock data
        Long id = 1L;
        String formType = "LendersStatementForm";
        String formName = "Form1";
        String deceasedName = "John Doe";

        // Set values using setters
        lendersStatementForm.setId(id);
        lendersStatementForm.setFormType(formType);
        lendersStatementForm.setFormName(formName);
        lendersStatementForm.setDeceasedName(deceasedName);

        // Verify the values using getters
        assertEquals(id, lendersStatementForm.getId());
        assertEquals(formType, lendersStatementForm.getFormType());
        assertEquals(formName, lendersStatementForm.getFormName());
        assertEquals(deceasedName, lendersStatementForm.getDeceasedName());
    }
}
