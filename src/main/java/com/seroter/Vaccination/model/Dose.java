package com.seroter.Vaccination.model;

import com.seroter.Vaccination.Enum.VaccineBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;// date of util(java) has both date & time but in sql date only date is there no time


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// It autometically add id to patient(like 1,2,3 etc)
    private int id;



    // uuid - universal unique identifier
    private  String serialNumber;





    @Enumerated(value = EnumType.STRING)
    private VaccineBrand vaccineBrand;



    @CreationTimestamp
    private Date dateOfVaccination;

    @OneToOne// 1st One ->Dose (current)  ,  2nd One -> Patient(connecting)
    @JoinColumn  // create the foreign key column -> patient_id
    Patient patient;



}
