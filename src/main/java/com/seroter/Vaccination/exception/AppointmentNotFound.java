package com.seroter.Vaccination.exception;

public class AppointmentNotFound extends RuntimeException{
    public AppointmentNotFound(String message){
        super(message);
    }
}
