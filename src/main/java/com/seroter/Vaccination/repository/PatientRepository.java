package com.seroter.Vaccination.repository;

import com.seroter.Vaccination.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
