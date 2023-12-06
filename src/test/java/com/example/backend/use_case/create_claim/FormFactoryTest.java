package com.example.backend.use_case.create_claim;

import com.example.backend.entities.CertificationOfDeathForm;
import com.example.backend.entities.Form;
import com.example.backend.entities.LendersStatementForm;
import com.example.backend.entities.LifeClaimInformationRequestForm;
import com.example.backend.repositories.FormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FormFactoryTest {

    @Mock
    private FormRepository<LifeClaimInformationRequestForm> lifeClaimInformationRequestFormRepository;

    @Mock
    private FormRepository<CertificationOfDeathForm> certificationOfDeathFormRepository;

    @Mock
    private FormRepository<LendersStatementForm> lendersStatementFormRepository;

    @InjectMocks
    private FormFactory formFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateLifeClaimInformationRequestFormSuccess() {
        // Stubbing repository methods
        when(lifeClaimInformationRequestFormRepository.save(any())).thenReturn(new LifeClaimInformationRequestForm());

        // Perform the action
        Form createdForm = formFactory.createLifeClaimInformationRequestForm();

        // Verify interactions and assertions
        verify(lifeClaimInformationRequestFormRepository, times(1)).save(any());
        assertNotNull(createdForm);
        assertTrue(createdForm instanceof LifeClaimInformationRequestForm);
        assertEquals("Life Claim Information Request Form", createdForm.getFormType());
        assertNotNull(createdForm.getLastModified());
    }

    @Test
    void testCreateCertificationOfDeathFormSuccess() {
        // Stubbing repository methods
        when(certificationOfDeathFormRepository.save(any())).thenReturn(new CertificationOfDeathForm());

        // Perform the action
        Form createdForm = formFactory.createCertificationOfDeathForm();

        // Verify interactions and assertions
        verify(certificationOfDeathFormRepository, times(1)).save(any());
        assertNotNull(createdForm);
        assertTrue(createdForm instanceof CertificationOfDeathForm);
        assertEquals("Certification of Death Form", createdForm.getFormType());
        assertNotNull(createdForm.getLastModified());
    }

    @Test
    void testCreateLendersStatementFormSuccess() {
        // Stubbing repository methods
        when(lendersStatementFormRepository.save(any())).thenReturn(new LendersStatementForm());

        // Perform the action
        Form createdForm = formFactory.createLendersStatementForm();

        // Verify interactions and assertions
        verify(lendersStatementFormRepository, times(1)).save(any());
        assertNotNull(createdForm);
        assertTrue(createdForm instanceof LendersStatementForm);
        assertEquals("Lender's Statement Form", createdForm.getFormType());
        assertNotNull(createdForm.getLastModified());
    }

    // Add more test cases as needed
}
