package com.example.backend.use_case.health_use_case;
import com.example.backend.entities.DemoClaim;
import com.example.backend.repositories.DemoClaimRepository;
import com.example.backend.response_model.CommonListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthServiceImplTest {

    @Mock
    private DemoClaimRepository demoClaimRepository;

    @InjectMocks
    private HealthServiceImpl healthService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateDemoClaimSuccess() {
        // Mock data
        String policyNumber = "123";

        // Stubbing repository methods
        when(demoClaimRepository.save(any())).thenReturn(new DemoClaim());

        // Perform the action
        CommonListResponse response = healthService.createDemoClaim(policyNumber);

        // Verify interactions and assertions
        verify(demoClaimRepository, times(1)).save(any());
        assertNotNull(response);
        assertEquals("Demo Claim Created", response.getMessage());
        assertEquals(200, response.getStatus());
    }

    @Test
    void testGetDemoClaimSuccess() {
        // Mock data
        String policyNumber = "123";
        ArrayList<DemoClaim> demoClaims = new ArrayList<>();
        demoClaims.add(new DemoClaim());

        // Stubbing repository methods
        when(demoClaimRepository.findByPolicyNumber(policyNumber)).thenReturn(demoClaims);

        // Perform the action
        CommonListResponse response = healthService.getDemoClaim(policyNumber);

        // Verify interactions and assertions
        verify(demoClaimRepository, times(1)).findByPolicyNumber(policyNumber);
        assertNotNull(response);
        assertEquals(demoClaims, response.getDetails());
        assertEquals("Demo Claim Found", response.getMessage());
        assertEquals(200, response.getStatus());
    }

    // Add more test cases as needed
}
