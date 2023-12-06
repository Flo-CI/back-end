package com.example.backend.use_case.file_upload;

import com.example.backend.entities.*;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.DocumentRepository;
import com.example.backend.repositories.FileRepository;
import com.example.backend.repositories.FormRepository;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.GenericException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileUploadServiceImplTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private FormRepository<Form> formRepository;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileUploadServiceImpl fileUploadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUploadFile_Success() {
        // Mock data
        String claimNumber = "123";
        String type = "Loss of Life Claim";
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);
        Form form = new LifeClaimInformationRequestForm();
        form.setFormType(type);
        Document document = new Document();
        document.setDocumentType(type);
        claim.setForms(List.of(form));
        claim.setDocuments(List.of(document));
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.pdf", "application/pdf", "content".getBytes());

        // Stubbing repository methods
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(claim);
        when(fileRepository.uploadFileToStorage(any(), any())).thenReturn("https://example.com/test.pdf");
        when(formRepository.save(any())).thenReturn(form);
        when(documentRepository.save(any())).thenReturn(document);

        // Perform the action
        CommonResponse<String> response = fileUploadService.uploadFile(multipartFile, claimNumber, type);

        // Verify interactions and assertions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(fileRepository, times(1)).uploadFileToStorage(any(), any());
        verify(formRepository, times(1)).save(form);
        verify(documentRepository, never()).save(any());
        assertNotNull(response);
        assertEquals("File uploaded successfully", response.getMessage());
        assertEquals(200, response.getStatus());
    }

    @Test
    void testUploadFile_InvalidClaimNumber() {
        // Mock data
        String claimNumber = "456";
        String type = "Loss of Life Claim";
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.pdf", "application/pdf", "content".getBytes());

        // Stubbing repository method
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(null);

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> fileUploadService.uploadFile(multipartFile, claimNumber, type));

        // Verify interactions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(fileRepository, never()).uploadFileToStorage(any(), any());
        verify(formRepository, never()).save(any());
        verify(documentRepository, never()).save(any());
    }

    @Test
    void testUploadFile_FileNotFound() {
        // Mock data
        String claimNumber = "123";
        String type = "Loss of Life Claim";
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);
        Form form = new CertificationOfDeathForm();
        form.setFormType("Certification of Death Form");
        Document document = new Document();
        document.setDocumentType("Death Certificate");
        claim.setForms(List.of(form));
        claim.setDocuments(List.of(document));
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.pdf", "application/pdf", "content".getBytes());

        // Stubbing repository method
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(claim);

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> fileUploadService.uploadFile(multipartFile, claimNumber, type));

        // Verify interactions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(fileRepository, never()).uploadFileToStorage(any(), any());
        verify(formRepository, never()).save(any());
        verify(documentRepository, never()).save(any());
    }

    // Add more test cases as needed
}
