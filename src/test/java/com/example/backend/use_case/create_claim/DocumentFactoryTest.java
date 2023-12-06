package com.example.backend.use_case.create_claim;
import com.example.backend.entities.Document;
import com.example.backend.repositories.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocumentFactoryTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentFactory documentFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateDocument_Success() {
        // Mock data
        String documentType = "Invoice";

        Document expectedDocument = new Document();
        expectedDocument.setDocumentType(documentType);
        expectedDocument.setLastModified(LocalDate.now());

        // Stubbing repository methods
        when(documentRepository.save(any())).thenReturn(expectedDocument);

        // Perform the action
        Document createdDocument = documentFactory.createDocument(documentType);

        // Verify interactions and assertions
        verify(documentRepository, times(1)).save(any());
        assertNotNull(createdDocument);
        assertEquals(expectedDocument.getDocumentType(), createdDocument.getDocumentType());
        assertEquals(expectedDocument.getLastModified(), createdDocument.getLastModified());
    }

    // Add more test cases as needed
}
