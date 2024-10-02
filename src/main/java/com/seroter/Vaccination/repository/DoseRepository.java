package com.seroter.Vaccination.repository;

import com.seroter.Vaccination.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose,Integer> {
}
