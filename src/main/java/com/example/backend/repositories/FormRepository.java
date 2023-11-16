package com.example.backend.repositories;

import com.example.backend.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormRepository<T extends Form> extends JpaRepository<T , Long> {

}
