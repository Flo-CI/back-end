package com.example.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationOfDeathForm extends Form{
   private String nameDeceased;
   private LocalDate dateOfDeath;
   private String immediateCauseOfDeath;
   private String dueTo;
   private LocalDate dateOfSymptomsOnset;
   private String causeOfDeath;
   private String placeOfDeath;
   private Boolean inquestPerformed;
   private Boolean autopsyPerformed;
   private String nameOfPhysician;
   private String titleOfPhysician;
   private String addressOfPhysician;
   private String phoneOfPhysician;
   private LocalDate dateSigned;

}
