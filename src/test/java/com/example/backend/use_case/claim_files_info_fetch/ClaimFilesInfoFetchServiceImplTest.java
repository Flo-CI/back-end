package com.example.backend.use_case.claim_files_info_fetch;
import com.example.backend.entities.*;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.FileModel;
import com.example.backend.response_model.GenericException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClaimFilesInfoFetchServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClaimFilesInfoFetchServiceImpl claimFilesInfoFetchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetClaimInfoSuccess() {
        // Mock data
        String policyNumber = "123";
        String claimNumber = "123";
        User user = new User();
        user.setPolicyNumber(policyNumber);
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);
        Form form1 = new LifeClaimInformationRequestForm();
        form1.setFormName("form1.pdf");
        form1.setFormType("Life Claim Information Request Form");
        form1.setLastModified(LocalDate.of(2023, 1, 1));
        Document document1 = new Document();
        document1.setDocumentName("doc1.pdf");
        document1.setDocumentType("Death Certificate");
        document1.setLastModified(LocalDate.of(2023, 2, 1));
        claim.setForms(List.of(form1));
        claim.setDocuments(List.of(document1));
        user.setClaims(List.of(claim));

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));

        // Perform the action
        CommonListResponse<FileModel> response = claimFilesInfoFetchService.getClaimInfo(claimNumber, policyNumber);

        // Verify interactions and assertions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        assertNotNull(response);
        assertEquals("Displaying files for claim number: " + claimNumber, response.getMessage());
        assertEquals(200, response.getStatus());
        assertEquals(2, response.getDataCount());
        List<FileModel> details = response.getDetails();
        assertNotNull(details);
        assertEquals(2, details.size());
        FileModel fileModel1 = details.get(0);
        assertEquals("form1.pdf", fileModel1.getFileName());
        assertEquals("Life Claim Information Request Form", fileModel1.getFileType());
        assertEquals(LocalDate.of(2023, 1, 1), fileModel1.getLastUpdated());
        FileModel fileModel2 = details.get(1);
        assertEquals("doc1.pdf", fileModel2.getFileName());
        assertEquals("Death Certificate", fileModel2.getFileType());
        assertEquals(LocalDate.of(2023, 2, 1), fileModel2.getLastUpdated());
    }

    @Test
    void testGetClaimInfoInvalidPolicyNumber() {
        // Mock data
        String policyNumber = "789";
        String claimNumber = "123";

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.empty());

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> claimFilesInfoFetchService.getClaimInfo(claimNumber, policyNumber));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
    }

    @Test
    void testGetClaimInfoInvalidClaimNumber() {
        // Mock data
        String policyNumber = "123";
        String claimNumber = "456";
        User user = new User();
        user.setPolicyNumber(policyNumber);
        Claim claim = new Claim();
        claim.setClaimNumber("C123"); // Different claim number
        user.setClaims(List.of(claim));

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> claimFilesInfoFetchService.getClaimInfo(claimNumber, policyNumber));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
    }

    // Add more test cases as needed
}
