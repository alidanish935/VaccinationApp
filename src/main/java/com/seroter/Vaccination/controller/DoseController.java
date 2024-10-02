package com.seroter.Vaccination.controller;


import com.seroter.Vaccination.Enum.VaccineBrand;
import com.seroter.Vaccination.model.Dose;
import com.seroter.Vaccination.model.Patient;
import com.seroter.Vaccination.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dose")
public class DoseController {
    @Autowired
    DoseService doseService;

//    @PostMapping("/add")
//    public Dose addDose(@RequestParam int patientId , @RequestParam VaccineBrand vacBrand){
//        return doseService.addDose(patientId,vacBrand);
//
//    }
@PostMapping("/add")
public Dose addDose(@RequestParam int patientId){
    return doseService.addDose(patientId);

}
    @GetMapping("/getWithVaccine")
    public ResponseEntity getPatientWithVaccine(@RequestParam("vaccine") VaccineBrand vaccine){
        try{
            List<Patient> res = doseService.getPatientWithVaccine(vaccine);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
