package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Inheritance
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Form {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    private String formType;

    private String formName;
    private LocalDate lastModified;

    private String fileUrl;
}
