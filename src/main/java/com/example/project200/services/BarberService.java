package com.example.project200.services;

import com.example.project200.entities.Reservation;
import com.example.project200.entities.Barber;
import com.example.project200.entities.Saloon;
import com.example.project200.handlers.AlreadyExistsException;
import com.example.project200.handlers.NotFoundException;
import com.example.project200.handlers.UniqueFieldException;
import com.example.project200.repositories.ReservationRepository;
import com.example.project200.repositories.BarberRepository;
import com.example.project200.repositories.SaloonRepository;
import com.example.project200.requestDto.ReservationReqDto;
import com.example.project200.requestDto.BarberReqDto;
import com.example.project200.responseDto.BarberResDto;
import com.example.project200.responseDto.ReservationResDto;
import com.example.project200.responseDto.SaloonResDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BarberService {
    private final BarberRepository barberRepository;
    private final ReservationRepository reservationRepository;
    private final SaloonRepository saloonRepository;
    private final ModelMapper modelMapper;

//    public String setAppointment(ReservationReqDto reservationReqDto) {
//        Reservation reservation = modelMapper.map(reservationReqDto, Reservation.class);
//        Reservation saved = reservationRepository.findById(reservation.getId()).orElse(null);
//
//        if (saved != null) {
//            return "Reservation already exists";
//        }
//        else {
//            reservationRepository.save(reservation);
//            Barber barber = reservation.getBarber();
//            List<Reservation> reservations = barber.getReservations();
//            reservations.add(reservation);
//            barber.setReservations(reservations);
//            return "Reservation added successfully";
//        }
//    }
//    public List<ReservationResDto> getAllAppointments() {
//        return reservationRepository.findAll()
//                .stream()
//                .map(reservation -> modelMapper.map(reservation, ReservationResDto.class))
//                .collect(Collectors.toList());
//    }
//    public String updateAppointment(ReservationReqDto reservationReqDto) {
//        Reservation reservation = modelMapper.map(reservationReqDto, Reservation.class);
//        Reservation existedReservation = reservationRepository.findById(reservation.getId()).orElse(null);
//        if (existedReservation == null) {
//            return "Reservation not found";
//        }
//        else {
//            existedReservation.setId(reservation.getId());
//
//
//            return "Reservation updated successfully";
//        }
//    }
//    public String deleteAppointment(Long Id) {
//        Reservation reservation = reservationRepository.findById(Id).orElse(null);
//        if (reservation == null) {
//            return "Reservation not found";
//        }
//        else {
//            reservationRepository.delete(reservation);
//            return "Reservation deleted successfully";
//        }
//    }

    public String createBarber(BarberReqDto barberReqDto) {
        Barber barber = modelMapper.map(barberReqDto, Barber.class);

        // Check if Barber already exists
        if (barberRepository.existsById(barber.getId())) {
            throw new AlreadyExistsException("Barber with ID " + barber.getId() + " already exists");
        }
        if (barberRepository.existsByName(barberReqDto.getName())) {
            throw new UniqueFieldException("Barber with name " + barber.getName() + " already exists");
        }


        // Find associated Saloon
        Saloon saloon = saloonRepository.findById(barberReqDto.getSaloonId())
                .orElseThrow(() -> new NotFoundException("Saloon with ID " + barberReqDto.getSaloonId() + " not found"));

        // Associate Barber with Saloon
        List<Barber> barbers = saloon.getBarbers();
        barbers.add(barber);
        saloon.setBarbers(barbers);

        barber.setSaloon(saloon);

        saloonRepository.save(saloon);
        barberRepository.save(barber);

        return "Barber created successfully";
    }

    public String updateBarber(BarberReqDto barberReqDto, Long id) {
        // Check if Barber exists
        Barber existingBarber = barberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Barber with ID " + id + " not found"));

        // Update Barber details
        existingBarber.setName(barberReqDto.getName());
        barberRepository.save(existingBarber);

        return "Barber updated successfully";
    }

    public String deleteBarber(Long id) {
        // Check if Barber exists
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Barber with ID " + id + " not found"));

        barberRepository.delete(barber);

        return "Barber deleted successfully";
    }

    public List<BarberResDto> getAllBarbers() {
        return barberRepository.findAll()
                .stream()
                .map(barber -> modelMapper.map(barber, BarberResDto.class))
                .collect(Collectors.toList());
    }

    public List<BarberResDto> getBarbersBySaloon(Long saloonId) {
        Saloon saloon = saloonRepository.findById(saloonId)
                .orElseThrow(() -> new NotFoundException("Saloon with ID " + saloonId + " not found"));

        return barberRepository.findBarbersBySaloon(saloon)
                .stream()
                .map(barber -> modelMapper.map(barber, BarberResDto.class))
                .collect(Collectors.toList());
    }
}