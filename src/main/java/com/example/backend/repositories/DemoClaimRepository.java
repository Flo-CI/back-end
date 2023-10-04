package com.example.backend.repositories;

import com.example.backend.entities.DemoClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DemoClaimRepository extends JpaRepository<DemoClaim, Long> {

    public ArrayList<DemoClaim> findByPolicyNumber(String policyNumber);

}
