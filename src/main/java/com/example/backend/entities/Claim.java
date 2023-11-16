package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claim {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String claimNumber;

    private String status;

    private String type;

    private LocalDate dateCreated;

    private Double amountInsured;

    @OneToMany
    private List<Form> forms = new ArrayList<>();

    @OneToMany
    private List<Document> documents = new ArrayList<>();

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void addForm(Form form) {
        forms.add(form);
    }
}
