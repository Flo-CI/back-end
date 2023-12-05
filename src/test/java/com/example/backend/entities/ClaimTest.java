package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClaimTest {

    private Claim claim;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testClaimFields() {
        claim = new Claim();

        // Mock data
        String claimNumber = "C12345";
        String status = "Pending";
        String type = "Auto";
        LocalDate dateCreated = LocalDate.of(2023, 1, 1);
        Double amountInsured = 1000.0;

        // Set the data
        claim.setClaimNumber(claimNumber);
        claim.setStatus(status);
        claim.setType(type);
        claim.setDateCreated(dateCreated);
        claim.setAmountInsured(amountInsured);

        // Verify the values
        assertEquals(claimNumber, claim.getClaimNumber());
        assertEquals(status, claim.getStatus());
        assertEquals(type, claim.getType());
        assertEquals(dateCreated, claim.getDateCreated());
        assertEquals(amountInsured, claim.getAmountInsured());
    }

    @Test
    void testAddDocument() {
        claim = new Claim();

        // Mock data
        Document document = new Document(1L, "PDF", "Document1", LocalDate.now(), "http://example.com/document1");

        // Invoke the method
        claim.addDocument(document);

        // Verify that the documents list is updated
        assertEquals(1, claim.getDocuments().size());
        assertEquals(document, claim.getDocuments().get(0));
    }

    @Test
    void testAddForm() {
        claim = new Claim();

        // Mock data
        Form form = new ConcreteForm(1L, "ConcreteForm", "Form1", LocalDate.now(), "http://example.com/form1");

        // Invoke the method
        claim.addForm(form);

        // Verify that the forms list is updated
        assertEquals(1, claim.getForms().size());
        assertEquals(form, claim.getForms().get(0));
    }

    // Concrete implementation of the abstract Form class for testing
    private static class ConcreteForm extends Form {
        public ConcreteForm(Long id, String formType, String formName, LocalDate lastModified, String fileUrl) {
            super(id, formType, formName, lastModified, fileUrl);
        }
    }
}