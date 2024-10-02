package com.seroter.Vaccination.service;

import com.seroter.Vaccination.exception.DoctorNotFoundException;
import com.seroter.Vaccination.model.Doctor;
import com.seroter.Vaccination.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    public Doctor getDoctor(int docId) {
        Optional<Doctor> optional = doctorRepository.findById(docId);

        if(optional.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor Id");
        }
        Doctor doctor = optional.get();
        return doctor;
    }

    public String deleteDoctor(int docId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(docId);
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("invalid doctor id");
        }
         doctorRepository.deleteById(docId);
         return "Doctor deleted succesfully";
    }

    public List<Doctor> getAllDoctor() {
        List<Doctor> doctors = doctorRepository.findAll();
        if(doctors.isEmpty()){
            throw new DoctorNotFoundException("No doctor available");
        }
        return doctors;
    }
}
