package com.example.backend.use_case.create_claim;

import com.example.backend.entities.Document;
import com.example.backend.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DocumentFactory {

    @Autowired
    private DocumentRepository documentRepository;
    public Document createDocument(String documentType) {
        Document document = new Document();
        document.setDocumentType(documentType);
        document.setLastModified(LocalDate.now());
        documentRepository.save(document);

        return document;
    }
}
