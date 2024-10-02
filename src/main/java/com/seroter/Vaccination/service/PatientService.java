package com.seroter.Vaccination.service;

import com.seroter.Vaccination.Enum.Gender;
import com.seroter.Vaccination.dto.request.PatientRequest;
import com.seroter.Vaccination.dto.response.PatientResponse;
import com.seroter.Vaccination.exception.PatientNotFoundException;
import com.seroter.Vaccination.model.Patient;
import com.seroter.Vaccination.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    public PatientResponse addPatient(PatientRequest patientRequest) {

        Patient patient = new Patient();
        patient.setName(patientRequest.getName());
        patient.setAge(patientRequest.getAge());
        patient.setVaccinated(false);
        patient.setGender(patientRequest.getGender());
        patient.setEmailId(patientRequest.getEmailId());

        Patient savedPatient = patientRepository.save(patient);

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(savedPatient.getName());
        patientResponse.setVaccinated(savedPatient.isVaccinated());
        patientResponse.setEmailId(savedPatient.getEmailId());
        return  patientResponse;
    }

       public PatientResponse getPatient(int id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("invalid patient id");
        }
        Patient patient = patientOptional.get();

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(patient.getName());
        patientResponse.setVaccinated(patient.isVaccinated());
        patientResponse.setEmailId(patient.getEmailId());
        return patientResponse;
    }

    public List<PatientResponse> getAllPatientWithGender(Gender gender) {
        // first get all patient
        List<Patient> patientOptional = patientRepository.findAll();

        List<PatientResponse> list = new ArrayList<>();
        for(Patient patient: patientOptional){
            if(patient.getGender()==gender){
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponse.setEmailId(patient.getEmailId());
                list.add(patientResponse);
            }
        }
        return list;
    }

    public List<PatientResponse> getAllVaccinatedPatByAge(int age, boolean isVaccinated) {
        List<Patient> allPatients = patientRepository.findAll();

        List<PatientResponse> list = new ArrayList<>();
        for(Patient patient: allPatients){
            if(patient.getAge()>age &&patient.isVaccinated()==isVaccinated){
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setVaccinated(patient.isVaccinated());
                patientResponse.setEmailId(patient.getEmailId());
                list.add(patientResponse);
            }
        }
       return list;
    }

    public List<PatientResponse> getAllVaccinatedPatByGender(boolean isVaccinated, Gender gender) {
        List<Patient> allPatients = patientRepository.findAll();

        List<PatientResponse>list = new ArrayList<>();
        for(Patient patient:allPatients){
          if(patient.isVaccinated()==isVaccinated && patient.getGender() ==gender){
              PatientResponse patientResponse = new PatientResponse();
              patientResponse.setName(patient.getName());
              patientResponse.setEmailId(patient.getEmailId());
              patientResponse.setVaccinated(patient.isVaccinated());
              list.add(patientResponse);
          }
        }
        return  list;
    }

    public  List<PatientResponse> changeVaccStatus() {
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientResponse> list= new ArrayList<>();
        for (Patient patient :allPatients){
            // changing in db
            patient.setVaccinated(!patient.isVaccinated());
            patientRepository.save(patient);

            // setting response
            PatientResponse patientResponse = new PatientResponse();
            patientResponse.setName(patient.getName());
            patientResponse.setVaccinated(patient.isVaccinated());
            patientResponse.setEmailId(patient.getEmailId());
            list.add(patientResponse);
        }
        return  list;
    }


}
