package com.seroter.Vaccination.service;

import com.seroter.Vaccination.dto.response.AppointmentResponse;
import com.seroter.Vaccination.dto.response.PatientResponse;
import com.seroter.Vaccination.exception.AppointmentNotFound;
import com.seroter.Vaccination.exception.DoctorNotFoundException;
import com.seroter.Vaccination.exception.PatientNotFoundException;
import com.seroter.Vaccination.model.Appointment;
import com.seroter.Vaccination.model.Doctor;
import com.seroter.Vaccination.model.Patient;
import com.seroter.Vaccination.repository.AppointmentRepository;
import com.seroter.Vaccination.repository.DoctorRepository;
import com.seroter.Vaccination.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static com.seroter.Vaccination.Enum.AppointmentStatus.BOOKED;

@Service
public class AppointmentService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;
    public AppointmentResponse bookAppointment(int patId, int docId) {
        Optional<Patient> optionalPatient = patientRepository.findById(patId);
        if(optionalPatient.isEmpty()){
            throw new PatientNotFoundException("invalid patient id");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(docId);
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("invalid Doctor id");
        }

        Patient patient = optionalPatient.get();
        Doctor doctor = optionalDoctor.get();

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setStatus(BOOKED);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedAppointment= appointmentRepository.save(appointment);

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentId(savedAppointment.getAppointmentId());
        appointmentResponse.setDateOfAppointment(savedAppointment.getDateOfAppointment());
        appointmentResponse.setStatus(savedAppointment.getStatus());
        appointmentResponse.setDoctorName(savedAppointment.getDoctor().getName());

        Patient savedPatient = savedAppointment.getPatient();
        PatientResponse patientResponse =new PatientResponse();

        patientResponse.setName(savedPatient.getName());
        patientResponse.setVaccinated(savedPatient.isVaccinated());
        patientResponse.setEmailId(savedPatient.getEmailId());

        appointmentResponse.setPatientResponse(patientResponse);

        return appointmentResponse;
    }

    public List<AppointmentResponse> getAllAppAfterDate(Date date) {
        List<Appointment> allAppointments = appointmentRepository.findAll();

        List<AppointmentResponse> list = new ArrayList<>();
        for (Appointment appointment :allAppointments){
            if (appointment.getDateOfAppointment().after(date)){
                AppointmentResponse appointmentResponse = new AppointmentResponse();
                appointmentResponse.setAppointmentId(appointment.getAppointmentId());
                appointmentResponse.setDateOfAppointment(appointment.getDateOfAppointment());
                appointmentResponse.setStatus(appointment.getStatus());
                appointmentResponse.setDoctorName(appointment.getDoctor().getName());

                PatientResponse patientRespons = new PatientResponse();
                patientRespons.setName(appointment.getPatient().getName());
                patientRespons.setVaccinated(appointment.getPatient().isVaccinated());
                patientRespons.setEmailId(appointment.getPatient().getEmailId());

                appointmentResponse.setPatientResponse(patientRespons);
                list.add(appointmentResponse);
            }
        }
        return list;
    }

    public List<AppointmentResponse> getAllAppOfDoctor(int docId) {
        List<Appointment> optionalAppointment = appointmentRepository.findAll();
        if(optionalAppointment.isEmpty()){
            throw new AppointmentNotFound("Empty appointment list");
        }
        List<AppointmentResponse> res = new ArrayList<>();

        for(Appointment appointment:optionalAppointment){
            AppointmentResponse appointmentResponse = new AppointmentResponse();
            if(appointment.getDoctor().getId()==docId){
                appointmentResponse.setAppointmentId(appointment.getAppointmentId());
                appointmentResponse.setStatus(appointment.getStatus());
                appointmentResponse.setDoctorName(appointment.getDoctor().getName());
                appointmentResponse.setDateOfAppointment(appointment.getDateOfAppointment());

                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(appointment.getPatient().getName());
                patientResponse.setEmailId(appointment.getPatient().getEmailId());
                patientResponse.setVaccinated(appointment.getPatient().isVaccinated());

                appointmentResponse.setPatientResponse(patientResponse);
            }
            res.add(appointmentResponse);
        }
        return res;
    }

    public List<Appointment> getAllAppointment() {
        List<Appointment> lists = appointmentRepository.findAll();
        if(lists.isEmpty()){
            throw new AppointmentNotFound("Appointment not found");
        }
      return lists;
    }

    public AppointmentResponse getAppDetailOfPatient(int patId) {
        List<Appointment> lists = appointmentRepository.findAll();
        if(lists.isEmpty()){
            throw new AppointmentNotFound("Appointment not found");
        }
        Optional<Patient>optionalPatient  = patientRepository.findById(patId);
        if(optionalPatient.isEmpty()){
            throw new PatientNotFoundException("Invalid Patient");
        }

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        for(Appointment appointment: lists){
            if(appointment.getPatient().getId()==patId){
                appointmentResponse.setAppointmentId(appointment.getAppointmentId());
                appointmentResponse.setStatus(appointment.getStatus());
                appointmentResponse.setDateOfAppointment(appointment.getDateOfAppointment());
                appointmentResponse.setDoctorName(appointment.getDoctor().getName());

                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setVaccinated(appointment.getPatient().isVaccinated());
                patientResponse.setName(appointment.getPatient().getName());
                patientResponse.setEmailId(appointment.getPatient().getEmailId());

                appointmentResponse.setPatientResponse(patientResponse);

            }
        }
        return  appointmentResponse;
    }
}
