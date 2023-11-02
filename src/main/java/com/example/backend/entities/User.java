package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    @Id
    private Long id;

    @Column(unique = true)
    private String policyNumber;

    private String password;

    @OneToMany
    private List<Claim> claims;

    public void addClaim(Claim claim) {
        claims.add(claim);
    }
}
