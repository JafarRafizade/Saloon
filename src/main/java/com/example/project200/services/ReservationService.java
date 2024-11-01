package com.example.project200.services;

import com.example.project200.entities.Barber;
import com.example.project200.entities.Reservation;
import com.example.project200.entities.Serve;
import com.example.project200.entities.Status;
import com.example.project200.handlers.NotFoundException;
import com.example.project200.repositories.BarberRepository;
import com.example.project200.repositories.ReservationRepository;
import com.example.project200.repositories.ServeRepository;
import com.example.project200.requestDto.ReservationReqDto;
import com.example.project200.responseDto.BarberResDto;
import com.example.project200.responseDto.ReservationResDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BarberRepository barberRepository;
    private final ServeRepository serveRepository;
    private final ModelMapper modelMapper;


    public String addReservation(ReservationReqDto reservationReqDto) {
        Reservation reservation = modelMapper.map(reservationReqDto, Reservation.class);
        Barber barber = barberRepository.findById(reservationReqDto.getBarberId())
                .orElseThrow(() -> new NotFoundException("Barber with ID " + reservationReqDto.getBarberId() + " not found"));

        reservation.setStatus(Status.PENDING);

        List<Serve> serves = serveRepository.findAllById(reservation.getServeIds());
        int totalDuration = serves.stream().mapToInt(Serve::getRequiredTime).sum();
        reservation.setEndTime(reservation.getStartTime().plusMinutes(totalDuration));

        reservationRepository.save(reservation);

        // Update barber's reservation list
        List<Long> reservationIds = barber.getReservationIds();
        reservationIds.add(reservation.getId());
        barberRepository.save(barber);

        return "Reservation added";
    }

    public String acceptRequest(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation with ID " + reservationId + " not found"));

        reservation.setStatus(Status.CONFIRMED);
        reservationRepository.save(reservation);
        return "Reservation accepted";
    }

    public String cancelRequest(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation with ID " + reservationId + " not found"));

        reservation.setStatus(Status.CANCELED);
        reservationRepository.save(reservation);
        return "Reservation cancelled";
    }

    public List<ReservationResDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationResDto.class))
                .collect(Collectors.toList());
    }

    public List<ReservationResDto> getReservationsByBarberId(Long barberId) {
        return reservationRepository.findReservationsByBarberId(barberId)
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationResDto.class))
                .collect(Collectors.toList());
    }


}
