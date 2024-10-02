package com.seroter.Vaccination.model;

import com.seroter.Vaccination.Enum.Gender;
import com.seroter.Vaccination.Enum.Specializaton;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// It autometically add id to patient(like 1,2,3 etc)
    private  int id;
    private String name;
    private int age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Specializaton specializaton;


}
