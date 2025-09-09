package com.example.project200.services;

import com.example.project200.entities.Appointment;
import com.example.project200.repositories.AppointmentRepository;
import com.example.project200.requestDto.NotificationReqDto;
import com.example.project200.responseDto.NotificationResDto;
import com.example.project200.services.serviceInterfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final AppointmentRepository appointmentRepo;

    @Override
    public NotificationResDto notificationForCustomer(NotificationReqDto notificationReqDto){
        Appointment appointmentsByCustomerIdAndDateAndStatus = appointmentRepo.findAppointmentsByCustomerIdAndDateAndStatus(notificationReqDto.getCustomerId(), notificationReqDto.getTime().minusMinutes(-1));
        NotificationResDto notificationResDto=new NotificationResDto();
        if(appointmentsByCustomerIdAndDateAndStatus==null){
            return new NotificationResDto();
        }
        notificationResDto.setStatus(appointmentsByCustomerIdAndDateAndStatus.getStatus());
        return notificationResDto;
    }



}

