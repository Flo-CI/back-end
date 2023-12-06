package com.example.backend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testUserFields() {
        // Mock data
        Long id = 1L;
        String policyNumber = "P123";
        String password = "password";

        // Set values using setters
        user.setId(id);
        user.setPolicyNumber(policyNumber);
        user.setPassword(password);

        // Verify the values using getters
        assertEquals(id, user.getId());
        assertEquals(policyNumber, user.getPolicyNumber());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testAddClaim() {
        user.setClaims(new ArrayList<Claim>());

        // Mock data
        Claim claim = new Claim(); // Use a real instance of Claim

        // Set up the method
        user.addClaim(claim);

        // Verify that the claims list is updated
        assertTrue(user.getClaims().contains(claim));
    }

    @Test
    void testUserClaims() {
        // Mock data
        Claim claim1 = new Claim(); // Use real instances of Claim
        Claim claim2 = new Claim();

        List<Claim> claimList = new ArrayList<>();
        claimList.add(claim1);
        claimList.add(claim2);

        // Set values using setters
        user.setClaims(claimList);

        // Verify that the claims list is set correctly
        assertEquals(claimList, user.getClaims());
    }
}