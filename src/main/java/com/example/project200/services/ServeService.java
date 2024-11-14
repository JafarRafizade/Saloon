package com.example.project200.services;

import com.example.project200.entities.Barber;
import com.example.project200.entities.Saloon;
import com.example.project200.entities.Serve;
import com.example.project200.handlers.NotFoundException;
import com.example.project200.handlers.UniqueFieldException;
import com.example.project200.repositories.BarberRepository;
import com.example.project200.repositories.SaloonRepository;
import com.example.project200.repositories.ServeRepository;
import com.example.project200.requestDto.ServeReqDto;
import com.example.project200.responseDto.ServeResDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServeService {
    private static final Logger log = LoggerFactory.getLogger(ServeService.class);
    private final ServeRepository serveRepository;
    private final BarberRepository barberRepository;
    private final ModelMapper modelMapper;
    private final SaloonRepository saloonRepository;

    public String addServe(ServeReqDto serveReqDto) {
        Serve serve = modelMapper.map(serveReqDto, Serve.class);

        // Retrieve Barber and Saloon, throw exception if not found
        Barber barber = barberRepository.findById(serveReqDto.getBarberId())
                .orElseThrow(() -> new NotFoundException("Barber with ID " + serveReqDto.getBarberId() + " not found"));
        Saloon saloon = saloonRepository.findById(serveReqDto.getSaloonId())
                .orElseThrow(() -> new NotFoundException("Saloon with ID " + serveReqDto.getSaloonId() + " not found"));
        if (serveRepository.existsByName(serveReqDto.getName())) {
            throw new UniqueFieldException("Serve with name " + barber.getName() + " already exists");
        }

        // Associate Serve with Saloon and Barber
        serve.setBarber(barber);
        serve.setSaloon(saloon);

        serveRepository.save(serve);

        saloon.getServes().add(serve);
        barber.getServes().add(serve);

        saloonRepository.save(saloon);
        barberRepository.save(barber);

        // Set relationships manually in Serve entity

        return "Serve with name " + serveReqDto.getName() + " added";
    }

    public String updateServe(ServeReqDto serveReqDto, Long id) {
        Serve existingServe = serveRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Serve with ID " + id + " not found"));

        // Update fields
        existingServe.setName(serveReqDto.getName());
        existingServe.setPrice(serveReqDto.getPrice());
        existingServe.setRequiredTime(serveReqDto.getRequiredTime());

        serveRepository.save(existingServe);
        return "Serve with name " + existingServe.getName() + " updated successfully";
    }

    public List<ServeResDto> getAllServes() {
        return serveRepository.findAll()
                .stream()
                .map(serve -> modelMapper.map(serve, ServeResDto.class))
                .collect(Collectors.toList());
    }
    public List<ServeResDto> getServesByBarberId(Long id) {
        return serveRepository.findServesBySaloonId(id)
                .stream()
                .map(serve -> modelMapper.map(serve, ServeResDto.class))
                .collect(Collectors.toList());
    }


    public String deleteServe(Long id) {
        Serve serve = serveRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Serve with ID " + id + " does not exist"));

        serveRepository.delete(serve);
        return "Serve with name " + serve.getName() + " deleted successfully";
    }
}


//    public String addServeToBarber(Long idServe, Long idBarber) {
//        ServeRepository serve = serveRepository.findById(idServe).orElse(null);
//        Barber barber = barberRepository.findById(idBarber).orElse(null);
//        if (barber == null) {
//            return "Barber with id " + idBarber + " does not exist";
//        }
//        else {
//            List<ServeRepository> barberServices = barber.getBarberServices();
//            barberServices.add(serve);
//            barber.setBarberServices(barberServices);
//            barberRepository.save(barber);
//            assert serve != null;
//            serve.setBarber(barber);
//            serveRepository.save(serve);
//            return "ServeRepository has successfully added to the barber";
//        }
//    }



