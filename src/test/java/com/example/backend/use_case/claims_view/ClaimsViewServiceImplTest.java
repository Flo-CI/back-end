package com.example.backend.use_case.claims_view;

import com.example.backend.entities.Claim;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.ClaimBaseModel;
import com.example.backend.response_model.CommonListResponse;
import com.example.backend.response_model.GenericException;
import com.example.backend.use_case.claims_view.ClaimsViewServiceImpl;
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

class ClaimsViewServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClaimsViewServiceImpl claimsViewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetClaims_Success() {
        // Mock data
        String policyNumber = "123";
        User user = new User();
        user.setPolicyNumber(policyNumber);
        Claim claim1 = new Claim();
        claim1.setClaimNumber("123");
        claim1.setStatus("Under Review");
        claim1.setType("Loss of Life Claim");
        claim1.setDateCreated(LocalDate.of(2023, 1, 1));
        Claim claim2 = new Claim();
        claim2.setClaimNumber("456");
        claim2.setStatus("Accepted");
        claim2.setType("Disability");
        claim2.setDateCreated(LocalDate.of(2023, 2, 1));
        user.setClaims(Arrays.asList(claim1, claim2));

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));

        // Perform the action
        CommonListResponse<ClaimBaseModel> response = claimsViewService.getClaims(policyNumber);

        // Verify interactions and assertions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
        assertNotNull(response);
        assertEquals("Claims Fetched", response.getMessage());
        assertEquals(200, response.getStatus());
        assertEquals(2, response.getDataCount());
        List<ClaimBaseModel> details = response.getDetails();
        assertNotNull(details);
        assertEquals(2, details.size());
        ClaimBaseModel claimBaseModel1 = details.get(0);
        assertEquals("123", claimBaseModel1.getClaimNumber());
        assertEquals("Under Review", claimBaseModel1.getStatus());
        assertEquals("Loss of Life Claim", claimBaseModel1.getType());
        assertEquals(LocalDate.of(2023, 1, 1), claimBaseModel1.getDateCreated());
        ClaimBaseModel claimBaseModel2 = details.get(1);
        assertEquals("456", claimBaseModel2.getClaimNumber());
        assertEquals("Accepted", claimBaseModel2.getStatus());
        assertEquals("Disability", claimBaseModel2.getType());
        assertEquals(LocalDate.of(2023, 2, 1), claimBaseModel2.getDateCreated());
    }

    @Test
    void testGetClaims_UserNotFound() {
        // Mock data
        String policyNumber = "789";

        // Stubbing repository method
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.empty());

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> claimsViewService.getClaims(policyNumber));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);
    }

    // Add more test cases as needed
}
