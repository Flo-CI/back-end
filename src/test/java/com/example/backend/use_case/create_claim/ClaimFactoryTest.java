package com.example.backend.use_case.create_claim;

import com.example.backend.entities.*;
import com.example.backend.repositories.ClaimRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClaimFactoryTest {

    @Mock
    private DocumentFactory documentFactory;

    @Mock
    private FormFactory formFactory;

    @Mock
    private ClaimRepository claimRepository;

    @InjectMocks
    private ClaimFactory claimFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateLossOfLifeClaim_Success() {
        // Mock data
        String policyNumber = "123";

        Claim expectedClaim = new Claim();
        expectedClaim.setClaimNumber("9876543");
        expectedClaim.setStatus("In Progress");
        expectedClaim.setType("Loss of Life Claim");
        expectedClaim.setDateCreated(LocalDate.now());

        Document deathCertificate = new Document();
        Document statementOfAccount = new Document();
        Form lifeClaimInformationRequestForm = new LifeClaimInformationRequestForm();
        Form certificationOfDeathForm = new CertificationOfDeathForm();
        Form lendersStatementForm = new LendersStatementForm();

        // Stubbing factory methods
        when(documentFactory.createDocument("Death Certificate")).thenReturn(deathCertificate);
        when(documentFactory.createDocument("Statement of Account")).thenReturn(statementOfAccount);
        when(formFactory.createLifeClaimInformationRequestForm()).thenReturn(lifeClaimInformationRequestForm);
        when(formFactory.createCertificationOfDeathForm()).thenReturn(certificationOfDeathForm);
        when(formFactory.createLendersStatementForm()).thenReturn(lendersStatementForm);

        // Stubbing repository method
        when(claimRepository.save(any())).thenReturn(expectedClaim);

        // Perform the action
        Claim createdClaim = claimFactory.createLossOfLifeClaim(policyNumber);

        // Verify interactions and assertions
        verify(documentFactory, times(2)).createDocument(anyString());
        verify(formFactory, times(1)).createLifeClaimInformationRequestForm();
        verify(formFactory, times(1)).createCertificationOfDeathForm();
        verify(formFactory, times(1)).createLendersStatementForm();
        verify(claimRepository, times(1)).save(any());

        assertNotNull(createdClaim);
        assertEquals(expectedClaim.getStatus(), createdClaim.getStatus());
        assertEquals(expectedClaim.getType(), createdClaim.getType());
        assertEquals(expectedClaim.getDateCreated(), createdClaim.getDateCreated());
        assertEquals(2, createdClaim.getDocuments().size());
        assertEquals(3, createdClaim.getForms().size());
    }

    // Add more test cases as needed
}
