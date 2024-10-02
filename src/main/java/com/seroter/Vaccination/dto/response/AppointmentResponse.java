package com.seroter.Vaccination.dto.response;

import com.seroter.Vaccination.Enum.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentResponse {
    private String appointmentId;

    private Date dateOfAppointment;
    private AppointmentStatus status;

    private PatientResponse patientResponse;
    private String doctorName;
}
