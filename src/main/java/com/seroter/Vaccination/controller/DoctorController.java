package com.seroter.Vaccination.controller;

import com.seroter.Vaccination.model.Doctor;
import com.seroter.Vaccination.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor doctor){
       return doctorService.addDoctor(doctor);
       // return "Doctor added Successfully";
    }

    @GetMapping("/get")
    public Doctor getDoctor(@RequestParam("id") int docId){
        return  doctorService.getDoctor(docId);
    }

    // getAll doctor
    @GetMapping("/getAll")
    public ResponseEntity getAllDoctor(){
        try {
            List<Doctor> list = doctorService.getAllDoctor();
            return new ResponseEntity(list,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteDoctor(@RequestParam int docId){
        try{
            String deletedDoctor = doctorService.deleteDoctor(docId);
            return new ResponseEntity(deletedDoctor, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
