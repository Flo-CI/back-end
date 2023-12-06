package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class DocumentTest {

    private Document document;

    @BeforeEach
    void setUp() {
        document = new Document();
    }

    @Test
    void testDocumentFields() {
        // Mock data
        Long id = 1L;
        String documentType = "PDF";
        String documentName = "Document1";
        LocalDate lastModified = LocalDate.of(2023, 1, 1);
        String fileUrl = "http://example.com/document1";

        // Set values using setters
        document.setId(id);
        document.setDocumentType(documentType);
        document.setDocumentName(documentName);
        document.setLastModified(lastModified);
        document.setFileUrl(fileUrl);

        // Verify the values using getters
        assertEquals(id, document.getId());
        assertEquals(documentType, document.getDocumentType());
        assertEquals(documentName, document.getDocumentName());
        assertEquals(lastModified, document.getLastModified());
        assertEquals(fileUrl, document.getFileUrl());
    }
}
