package com.example.backend.use_case.claim_rank;

import com.example.backend.response_model.ClaimBaseModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClaimRankComparatorTest {

    @Test
    void testCompare() {
        // Create ClaimBaseModel instances with different dateCreated values
        ClaimBaseModel claim1 = new ClaimBaseModel();
        claim1.setDateCreated(LocalDate.of(2022, 1, 1));

        ClaimBaseModel claim2 = new ClaimBaseModel();
        claim2.setDateCreated(LocalDate.of(2022, 2, 1));

        ClaimBaseModel claim3 = new ClaimBaseModel();
        claim3.setDateCreated(LocalDate.of(2022, 2, 15));

        // Create the comparator
        ClaimRankComparator comparator = new ClaimRankComparator();

        // Test the comparison
        assertTrue(comparator.compare(claim1, claim2) < 0); // claim1 is older than claim2
        assertTrue(comparator.compare(claim2, claim3) < 0); // claim2 is older than claim3
        assertTrue(comparator.compare(claim1, claim3) < 0); // claim1 is older than claim3
    }
}
