package com.example.project200.services;

import com.example.project200.entities.Barber;
import com.example.project200.entities.Saloon;
import com.example.project200.entities.Serve;
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

        // Retrieve Barber and Saloon from the database
        Barber barber = barberRepository.findById(serveReqDto.getBarberId()).orElse(null);
        if (barber == null) {
            return "Barber not found";
        }

        Saloon saloon = saloonRepository.findById(serveReqDto.getSaloonId()).orElse(null);
        if (saloon == null) {
            return "Saloon not found";
        }

        // Set relationships manually
        serve.setBarber(barber);
        serve.setSaloon(saloon);

        // Save Serve entity
        serveRepository.save(serve);

        return "Serve with name " + serveReqDto.getName() + " added";

    }
    public String updateServe(ServeReqDto serveReqDto, Long id) {
        Serve saved = serveRepository.findById(id).orElse(null);
        if (saved == null) {
            return "Barber service not found";


        }
        saved.setName(serveReqDto.getName());
        saved.setPrice(serveReqDto.getPrice());
        saved.setRequiredTime(serveReqDto.getRequiredTime());
        serveRepository.save(saved);
        return "ServeRepository with name " + saved.getName() + " updated";

    }

    public List<ServeResDto> getAllServes() {
        return serveRepository.findAll()
                .stream()
                .map(serve -> modelMapper.map(serve, ServeResDto.class))
                .collect(Collectors.toList());
    }

    //    public String updateServe(ServeReqDto serveReqDto, Long id) {
//        ServeRepository serve = serveRepository.findById(id).orElse(null);
//        ServeRepository updatedServe = modelMapper.map(serveReqDto, ServeRepository.class);
//        if (serve == null){
//            return "ServeRepository does not exist";
//        }
//        else {
//            serve.setId(updatedServe.getId());
//            return "ServeRepository has successfully updated";
//        }
//    }
    public String deleteServe(Long id) {
        Serve serve = serveRepository.findById(id).orElse(null);
        if (serve == null) {
            return "Serve does not exist";
        } else {
            serveRepository.delete(serve);
            return "Serve- has successfully deleted";
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


}
