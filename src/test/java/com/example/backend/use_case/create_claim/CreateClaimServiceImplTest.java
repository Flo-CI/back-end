package com.example.backend.use_case.create_claim;

import com.example.backend.entities.Claim;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.GenericException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateClaimServiceImplTest {

    @Mock
    private ClaimFactory claimFactory;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateClaimServiceImpl createClaimService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateClaimSuccess() {
        // Mock data
        String policyNumber = "123";
        String type = "Loss of Life Claim";

        User user = new User();
        user.setPolicyNumber(policyNumber);
        user.setClaims(new ArrayList<>());

        Claim claim = new Claim();
        claim.setClaimNumber("C123");

        // Stubbing repository methods
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));
        when(claimFactory.createLossOfLifeClaim(policyNumber)).thenReturn(claim);

        // Perform the action
        CommonResponse<String> response = createClaimService.createClaim(policyNumber, type);

        // Verify interactions and assertions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        verify(claimFactory, times(1)).createLossOfLifeClaim(policyNumber);
        verify(userRepository, times(1)).save(user);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertTrue(response.getMessage().contains("Loss of Life Claim"));
        assertTrue(response.getMessage().contains("C123"));
    }

    @Test
    void testCreateClaim_InvalidPolicyNumber() {
        // Mock data
        String policyNumber = "456";
        String type = "Loss of Life Claim";

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.empty());

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> createClaimService.createClaim(policyNumber, type));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        verify(claimFactory, never()).createLossOfLifeClaim(any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void testCreateClaimInvalidClaimType() {
        // Mock data
        String policyNumber = "789";
        String type = "Invalid Claim Type";

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(new User()));

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> createClaimService.createClaim(policyNumber, type));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        verify(claimFactory, never()).createLossOfLifeClaim(any());
        verify(userRepository, never()).save(any());
    }

    // Add more test cases as needed
}
