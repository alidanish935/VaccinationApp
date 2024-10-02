package com.seroter.Vaccination.service;

import com.seroter.Vaccination.Enum.VaccineBrand;
import com.seroter.Vaccination.exception.PatientNotFoundException;
import com.seroter.Vaccination.model.Dose;
import com.seroter.Vaccination.model.Patient;
import com.seroter.Vaccination.repository.DoseRepository;
import com.seroter.Vaccination.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoseRepository doseRepository;
    public Dose addDose(int patientId) {
        Optional<Patient>patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("invalid Patient");
        }
        Patient patient = patientOptional.get();
        if( patient.isVaccinated()){
            throw  new RuntimeException("Patient already vaccinated");
        }
        patient.setVaccinated(true);
        Dose dose = new Dose();

        if(patient.getAge()>=18){
            dose.setVaccineBrand(VaccineBrand.COVAXINE);
        }else{
            dose.setVaccineBrand(VaccineBrand.COVISHIELD);
        }

        dose.setSerialNumber(String.valueOf(UUID.randomUUID()));
        dose.setPatient(patient);
        patientRepository.save(patient);
        return  doseRepository.save(dose);

    }

    public List<Patient> getPatientWithVaccine(VaccineBrand vaccine) {
        List<Patient>patientList = patientRepository.findAll();
        List<Dose>doses = doseRepository.findAll();
        List<Patient>ans = new ArrayList<>();

        for(Dose dose:doses){
            if(dose.getVaccineBrand()==vaccine){
                Patient patient = dose.getPatient();
                ans.add(patient);
            }
        }
        return ans;
    }
}
