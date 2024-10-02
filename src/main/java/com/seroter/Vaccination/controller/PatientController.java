package com.seroter.Vaccination.controller;

import com.seroter.Vaccination.Enum.Gender;
import com.seroter.Vaccination.dto.request.PatientRequest;
import com.seroter.Vaccination.dto.response.PatientResponse;
import com.seroter.Vaccination.model.Patient;
import com.seroter.Vaccination.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public PatientResponse addPatient(@RequestBody PatientRequest patientRequest){
        return patientService.addPatient(patientRequest);
    }

    @PostMapping("/changeVacStatus")
    public List<PatientResponse> changeVaccStatus(){

        return patientService.changeVaccStatus();
    }

    @GetMapping("/get")
    public PatientResponse getPatient(@RequestParam("id") int pId){

        return patientService.getPatient(pId);
    }


    //find all patient with gender
    @GetMapping("/getAll/gender/{gender}")
    public List<PatientResponse> getAllPatientWithGender(@PathVariable("gender") Gender gender){
        return patientService.getAllPatientWithGender(gender);
    }

    //all vaccinated above specific age
    @GetMapping("/getAll")
    public List<PatientResponse> getAllVaccinatedPatByAge(@RequestParam int age , @RequestParam boolean isVaccinated){
        return patientService.getAllVaccinatedPatByAge(age,isVaccinated);
    }

    //all vaccinated with gender
    @GetMapping("/getAll/{isVaccinated}/{gender}")
    public  List<PatientResponse> getAllVaccinatedPatByGender(@PathVariable("isVaccinated") boolean isVaccinated,@PathVariable("gender") Gender gender){
        return patientService.getAllVaccinatedPatByGender(isVaccinated,gender);
    }

}
