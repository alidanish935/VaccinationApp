package com.seroter.Vaccination.model;

import com.seroter.Vaccination.Enum.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity // to create table in db
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// It autometically add id to patient(like 1,2,3 etc)
    private int id;

    private String name;

    private int age;


    private  boolean vaccinated;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(unique = true,nullable = false)
    private String emailId;


}
