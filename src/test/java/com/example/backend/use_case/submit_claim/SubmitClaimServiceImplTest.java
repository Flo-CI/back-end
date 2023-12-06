package com.example.backend.use_case.submit_claim;

import com.example.backend.entities.Claim;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.GenericException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubmitClaimServiceImplTest {

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private SubmitClaimServiceImpl submitClaimService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSubmitClaimSuccess() {
        // Mock data
        String claimNumber = "123";
        Claim claim = new Claim();
        claim.setClaimNumber(claimNumber);

        // Stubbing repository method
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(claim);
        when(claimRepository.save(any())).thenReturn(claim);

        // Perform the action
        CommonResponse<String> response = submitClaimService.submitClaim(claimNumber);

        // Verify interactions and assertions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(claimRepository, times(1)).save(claim);
        assertAll(
                () -> assertEquals(200, response.getStatus()),
                () -> assertEquals("Claim submitted successfully", response.getMessage()));
    }

    @Test
    void testSubmitClaimClaimNotFound() {
        // Mock data
        String claimNumber = "456";

        // Stubbing repository method
        when(claimRepository.findByClaimNumber(claimNumber)).thenReturn(null);

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> submitClaimService.submitClaim(claimNumber));

        // Verify interactions
        verify(claimRepository, times(1)).findByClaimNumber(claimNumber);
        verify(claimRepository, never()).save(any());
    }

    // Add more test cases as needed
}
