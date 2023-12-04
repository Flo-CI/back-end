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
import static org.mockito.Mockito.*;

class ClaimRankServiceImplTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ClaimRankComparator claimRankComparator;

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
        ClaimBaseModel claim1 = new ClaimBaseModel();
        claim1.setClaimNumber("claim1");
        claim1.setStatus("Pending");
        claim1.setType("TypeA");
        claim1.setDateCreated(LocalDate.now());

        ClaimBaseModel claim2 = new ClaimBaseModel();
        claim2.setClaimNumber("claim2");
        claim2.setStatus("Approved");
        claim2.setType("TypeB");
        claim2.setDateCreated(LocalDate.now());

        List<ClaimBaseModel> allClaims = Arrays.asList(claim1, claim2);
        User user = new User();
        Claim userClaim1 = new Claim();
        userClaim1.setClaimNumber("claim1");
        userClaim1.setDateCreated(LocalDate.now());
        user.setClaims(Collections.singletonList(userClaim1));

        // Mock repository responses
        when(claimRepository.findAll()).thenReturn(Arrays.asList(
                new Claim(Long.valueOf(0), "claim1", "Pending", "TypeA", LocalDate.now(), 14.0, new ArrayList<>(),
                        new ArrayList<>()),
                new Claim(Long.valueOf(1), "claim2", "Pending", "TypeB", LocalDate.now(), 14.0, new ArrayList<>(),
                        new ArrayList<>())));
        when(userRepository.findByPolicyNumber(policyNumber)).thenReturn(Optional.of(user));

        // Mock sorting behavior
        when(claimRankComparator.compare(any(), any())).thenCallRealMethod();

        // Invoke the method
        CommonResponse<HashMap<String, Integer>> result = claimRankService.getClaimRanks(policyNumber);

        // Verify the result
        assertEquals(200, result.getStatus());
        assertEquals("Claim ranks fetched", result.getMessage());

        // Verify that the getAllClaims method is called
        verify(claimRepository, times(1)).findAll();

        // Verify that the findByPolicyNumber method is called
        verify(userRepository, times(1)).findByPolicyNumber(policyNumber);

    }

    @Test
    void testComputeRanks() {
        // Mock data
        Set<String> userClaimNumbers = new HashSet<>(Arrays.asList("claim1", "claim2", "claim3"));

        ClaimBaseModel claim1 = new ClaimBaseModel();
        claim1.setClaimNumber("claim1");
        claim1.setStatus("Pending");
        claim1.setType("TypeA");
        claim1.setDateCreated(LocalDate.now());

        ClaimBaseModel claim2 = new ClaimBaseModel();
        claim2.setClaimNumber("claim2");
        claim2.setStatus("Approved");
        claim2.setType("TypeB");
        claim2.setDateCreated(LocalDate.now());

        ClaimBaseModel claim3 = new ClaimBaseModel();
        claim3.setClaimNumber("claim3");
        claim3.setStatus("Rejected");
        claim3.setType("TypeC");
        claim3.setDateCreated(LocalDate.now());

        List<ClaimBaseModel> allClaims = Arrays.asList(claim1, claim2, claim3);

        // Invoke the method
        HashMap<String, Integer> result = claimRankService.computeRanks(userClaimNumbers, allClaims);

        // Verify the result
        assertEquals(3, result.size());
        assertEquals(1, result.get("claim1"));
        assertEquals(2, result.get("claim2"));
        assertEquals(3, result.get("claim3"));
    }
}
