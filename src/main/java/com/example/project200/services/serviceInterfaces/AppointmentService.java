package com.example.project200.services.serviceInterfaces;

import com.example.project200.entities.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    String confirm(Long appointmentId, Status status, Long barberId, String token);
    String addAppointment(Long barberId, Integer durationTime, LocalDateTime localDateTime, String token);
    String notifyCustomer(List<Long> id);
    String callToCustomer(Long appointmentId,Long barberId,String token);
}
