package com.amit.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amit.dev.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
