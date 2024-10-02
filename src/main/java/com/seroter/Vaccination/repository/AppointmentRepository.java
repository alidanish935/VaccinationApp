package com.seroter.Vaccination.repository;

import com.seroter.Vaccination.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
