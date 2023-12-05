package com.example.backend.use_case.add_basic_claim_information;

import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.entities.User;
import com.example.backend.entities.Claim;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.request_model.BasicClaimModel;
import com.example.backend.response_model.CommonResponse;
import com.example.backend.response_model.GenericException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasicClaimServiceImplTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BasicClaimServiceImpl basicClaimService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddBasicClaimInformationSuccess() {
        // Mock data
        BasicClaimModel basicClaimModel = new BasicClaimModel();
        basicClaimModel.setPolicyNumber("123");
        basicClaimModel.setClaimNumber("C123");
        basicClaimModel.setType("Medical");
        basicClaimModel.setStatus("Pending");
        basicClaimModel.setDateCreated("2023-01-01");

        User user = new User();
        user.setPolicyNumber("123");
        user.setClaims(new ArrayList<>());

        // Stubbing repository methods
        when(userRepository.findByPolicyNumber("123")).thenReturn(Optional.of(user));
        when(claimRepository.save(any())).thenReturn(new Claim());

        // Perform the action
        CommonResponse<String> response = basicClaimService.addBasicClaimInformation(basicClaimModel);

        // Verify interactions and assertions
        verify(userRepository, times(1)).findByPolicyNumber("123");
        verify(claimRepository, times(1)).save(any());
        verify(userRepository, times(1)).save(user);
        assertNotNull(response);
        assertEquals("Claim created", response.getMessage());
        assertEquals(201, response.getStatus());
    }

    @Test
    void testAddBasicClaimInformationUserNotFound() {
        // Mock data
        BasicClaimModel basicClaimModel = new BasicClaimModel();
        basicClaimModel.setPolicyNumber("456");

        // Stubbing repository method
        when(userRepository.findByPolicyNumber("456")).thenReturn(Optional.empty());

        // Perform the action and expect an exception
        assertThrows(GenericException.class, () -> basicClaimService.addBasicClaimInformation(basicClaimModel));

        // Verify interactions
        verify(userRepository, times(1)).findByPolicyNumber("456");
        verify(claimRepository, never()).save(any());
        verify(userRepository, never()).save(any());
    }

    // Add more test cases as needed
}
