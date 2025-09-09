package com.example.project200.services;

import com.example.project200.entities.*;
import com.example.project200.handlers.NotFoundException;
import com.example.project200.handlers.UnauthorizedException;
import com.example.project200.repositories.*;
import com.example.project200.services.serviceInterfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final BarberRepository barberRepository;
    private final MyUserRepository myUserRepository;
    private final CustomerRepository customerRepository;
    @Override
    public String confirm(Long appointmentId, Status status,Long barberId,String token){
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found by ID - " + appointmentId));
        MyUser myUserByBarberId = myUserRepository.findMyUserByBarberId(barberId);
        if(token==null || !token.equals(String.valueOf(myUserByBarberId.getId()))){
            throw new UnauthorizedException("You are not authorized to confirm this appointment.");
        }
        if(Objects.equals(appointment.getBarberId(), barberId)) {
            appointment.setStatus(status);
        }
        appointmentRepo.save(appointment);
        return "Confirm is successfully";
    }

    @Override
    public String callToCustomer(Long appointmentId,Long barberId,String token){
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment not found by ID - " + appointmentId));
        MyUser myUserByBarberId = myUserRepository.findMyUserByBarberId(barberId);
        if(token==null || !token.equals(String.valueOf(myUserByBarberId.getId()))){
            throw new UnauthorizedException("You are not authorized to confirm this appointment.");
        }
        if(Objects.equals(appointment.getBarberId(), barberId)){
            Customer customer = customerRepository.findById(appointment.getCustomerId())
                    .orElseThrow(() -> new NotFoundException("User not found by ID - " + appointment.getCustomerId()));


            return customer.getMyUser().getNumber();
        }
        return "null";

    }
    @Override
    public String addAppointment(Long barberId, Integer durationTime, LocalDateTime localDateTime, String token){
        barberRepository.findById(barberId)
                .orElseThrow(()->new NotFoundException("Barber not found by ID - "+barberId));
        MyUser myUserByBarberId = myUserRepository.findMyUserByBarberId(barberId);
        if(token==null || !token.equals(String.valueOf(myUserByBarberId.getId()))){
            throw new UnauthorizedException("You are not authorized to add this appointment.");
        }
        Appointment appointment=new Appointment();
        appointment.setStatus(Status.SUCCESS);
        appointment.setBarberId(barberId);
        appointment.setAppointmentDate(localDateTime);
        appointment.setDurationTime(durationTime*60);

        appointmentRepo.save(appointment);
        return "Add successfully";

    }

    @Override
    public String notifyCustomer(List<Long> id){
        List<Appointment> allById = appointmentRepo.findAllById(id);
        for (Appointment a : allById) {
            a.setStatus(Status.DONE);
            appointmentRepo.save(a);
        }
        return "Customer notify successfully";
    }





}
