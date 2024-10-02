package com.seroter.Vaccination.controller;

import com.seroter.Vaccination.dto.response.AppointmentResponse;
import com.seroter.Vaccination.dto.response.PatientResponse;
import com.seroter.Vaccination.model.Appointment;
import com.seroter.Vaccination.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
@Autowired
AppointmentService appointmentService;


    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestParam int patId, @RequestParam int docId){
        try {
            AppointmentResponse bookedAppointment = appointmentService.bookAppointment(patId,docId);
            return new ResponseEntity(bookedAppointment, HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity getAllAppointment(){
        try {
            List<Appointment>list = appointmentService.getAllAppointment();
            return new ResponseEntity(list,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
// get all the appointment after a particular date
    @GetMapping("/afterDate")
    public List<AppointmentResponse> getAllAppAfterDate(@RequestParam Date date){
        return  appointmentService.getAllAppAfterDate(date);
    }


// get all the appoitments with a particular doctor
    @GetMapping("/getAppOfDoctor")
    public ResponseEntity getAllAppOfDoctor(@RequestParam int docId){
        try{
            List<AppointmentResponse>list = appointmentService.getAllAppOfDoctor(docId);
            return  new ResponseEntity(list,HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // get appointment details of a particular patient

    @GetMapping("/getPatientDetail")
    public ResponseEntity getAppDetailOfPatient(@RequestParam int patId){
        try {
            AppointmentResponse appointmentResponse =appointmentService.getAppDetailOfPatient(patId);
            return new ResponseEntity(appointmentResponse,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}


// make api to change the status of appointment

