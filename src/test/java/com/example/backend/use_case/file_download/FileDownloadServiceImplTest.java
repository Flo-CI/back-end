package com.example.backend.use_case.file_download;

import com.example.backend.entities.*;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.FileRepository;
import com.example.backend.response_model.GenericException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileDownloadServiceImplTest {

    @Mock
    private FileRepository fileRepository;

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private FileDownloadServiceImpl fileDownloadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDownloadFile_Form_Success() {
        // Mock data
        String claimNumber = "C123";
        String type = "Loss of Life Claim";
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);
        Form form = new LifeClaimInformationRequestForm();
        form.setFormType(type);
        form.setFileUrl("https://example.com/form.pdf");
        form.setFormName("MedicalForm");
        claim.addForm(form);

        // Stubbing repository methods
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(claim);
        when(fileRepository.downloadFileFromStorage("form.pdf")).thenReturn("MockFileContent".getBytes());

        // Perform the action
        ResponseEntity<byte[]> responseEntity = fileDownloadService.downloadFile(claimNumber, type);

        // Verify interactions and assertions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(fileRepository, times(1)).downloadFileFromStorage("form.pdf");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_PDF, responseEntity.getHeaders().getContentType());
        assertEquals(List.of("attachment; filename=MedicalForm"), responseEntity.getHeaders().get(HttpHeaders.CONTENT_DISPOSITION));
        assertArrayEquals("MockFileContent".getBytes(), responseEntity.getBody());
    }

    @Test
    void testDownloadFile_Document_Success() {
        // Mock data
        String claimNumber = "123";
        String type = "Death Certificate";
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);
        Document document = new Document();
        document.setDocumentType(type);
        document.setFileUrl("https://example.com/death_certificate.pdf");
        document.setDocumentName("death_certificate.pdf");
        claim.addDocument(document);

        // Stubbing repository methods
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(claim);
        when(fileRepository.downloadFileFromStorage("death_certificate.pdf")).thenReturn("MockFileContent".getBytes());

        // Perform the action
        ResponseEntity<byte[]> responseEntity = fileDownloadService.downloadFile(claimNumber, type);

        // Verify interactions and assertions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(fileRepository, times(1)).downloadFileFromStorage("death_certificate.pdf");
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_PDF, responseEntity.getHeaders().getContentType());
        assertEquals(List.of("attachment; filename=death_certificate.pdf"), responseEntity.getHeaders().get(HttpHeaders.CONTENT_DISPOSITION));
        assertArrayEquals("MockFileContent".getBytes(), responseEntity.getBody());
    }

    @Test
    void testDownloadFile_FileNotFound() {
        // Mock data
        String claimNumber = "C456";
        String type = "Certification of Death Form";
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);
        Form form = new LifeClaimInformationRequestForm();
        form.setFormType("Life Claim Information Request Form");
        claim.addForm(form);

        // Stubbing repository method
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(claim);

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> fileDownloadService.downloadFile(claimNumber, type));

        // Verify interactions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(fileRepository, never()).downloadFileFromStorage(any());
    }

    // Add more test cases as needed
}
