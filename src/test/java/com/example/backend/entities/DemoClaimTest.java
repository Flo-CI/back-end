package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DemoClaimTest {

    private DemoClaim demoClaim;

    @BeforeEach
    void setUp() {
        demoClaim = new DemoClaim();
    }

    @Test
    void testDemoClaimFields() {
        // Mock data
        Long id = 1L;
        String policyNumber = "P123";

        // Set values using setters
        demoClaim.setId(id);
        demoClaim.setPolicyNumber(policyNumber);

        // Verify the values using getters
        assertEquals(id, demoClaim.getId());
        assertEquals(policyNumber, demoClaim.getPolicyNumber());
    }
}