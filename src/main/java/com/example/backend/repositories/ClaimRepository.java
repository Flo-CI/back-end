package com.example.backend.repositories;

import com.example.backend.entities.Claim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    public Claim findByClaimNumber(String claimNumber);
}
