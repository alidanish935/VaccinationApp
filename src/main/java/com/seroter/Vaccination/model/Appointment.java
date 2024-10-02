package com.seroter.Vaccination.model;

import com.seroter.Vaccination.Enum.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// It autometically add id to patient(like 1,2,3 etc)
    private int id;
    private String appointmentId;

    @CreationTimestamp
    private Date dateOfAppointment;
    private AppointmentStatus status;

    @ManyToOne
            @JoinColumn
    Doctor doctor;

    @OneToOne
            @JoinColumn
    Patient patient;

}
