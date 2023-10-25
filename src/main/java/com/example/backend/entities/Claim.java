package com.example.backend.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    private String type;
    private String policyNumber;
    private String status;
    private String date;
    private String amount;
    private String description;
    private Document[] documents;
    private String[] comments;

    // TODO: Implement these methods
    public boolean accept() {
        return false;
    }

    public boolean reject() {
        return false;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
