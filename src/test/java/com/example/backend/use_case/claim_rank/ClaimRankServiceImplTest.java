package com.example.backend.use_case.claim_rank;

import com.example.backend.entities.Claim;
import com.example.backend.entities.User;
import com.example.backend.repositories.ClaimRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.response_model.ClaimBaseModel;
import com.example.backend.response_model.CommonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ClaimRankServiceImplTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClaimRankServiceImpl claimRankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClaimRanks() {
        // Mock data
        String policyNumber = "123";
        Claim claim1 = new Claim();
        claim1.setClaimNumber("C001");
        claim1.setStatus("Under Review");
        claim1.setDateCreated(LocalDate.now());

        Claim claim2 = new Claim();
        claim2.setClaimNumber("C002");
        claim2.setStatus("Under Review");
        claim2.setDateCreated(LocalDate.now());

        Claim claim3 = new Claim();
        claim3.setClaimNumber("C003");
        claim3.setStatus("Approved");
        claim3.setDateCreated(LocalDate.now());

        User user = new User();
        user.setClaims(Arrays.asList(new Claim(1L, "C001", "Under Review", policyNumber, null, null, null, null),
                new Claim(2L, "C002", "Under Review", policyNumber, null, null, null, null)));

        // Mock repository responses
        when(claimRepository.findAll()).thenReturn(Arrays.asList(claim1, claim2, claim3));
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));

        // Invoke the service method
        CommonResponse<HashMap<String, Integer>> result = claimRankService.getClaimRanks(policyNumber);

        // Verify the result
        assertEquals(200, result.getStatus());
        assertEquals("Claim ranks fetched", result.getMessage());
        HashMap<String, Integer> details = result.getDetails();
        assertEquals(2, details.size());
        assertEquals(1, details.get("C001"));
        assertEquals(2, details.get("C002"));
    }

    @Test
    void testComputeRanks() {
        // Mock data
        Set<String> userClaimNumbers = new HashSet<>(Arrays.asList("C001", "C003"));
        ClaimBaseModel cb1 = new ClaimBaseModel();
        cb1.setClaimNumber("C001");
        cb1.setStatus("Under Review");

        ClaimBaseModel cb2 = new ClaimBaseModel();
        cb2.setClaimNumber("C002");
        cb2.setStatus("Under Review");

        ClaimBaseModel cb3 = new ClaimBaseModel();
        cb3.setClaimNumber("C003");
        cb3.setStatus("Approved");

        List<ClaimBaseModel> allClaims = Arrays.asList(cb1, cb2, cb3);

        // Invoke the computeRanks method
        HashMap<String, Integer> result = claimRankService.computeRanks(userClaimNumbers, allClaims);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals(1, result.get("C001"));
        assertEquals(3, result.get("C003"));
    }
}