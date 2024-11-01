package com.example.project200.services;

import com.example.project200.entities.Saloon;
import com.example.project200.handlers.AlreadyExistsException;
import com.example.project200.handlers.NotFoundException;
import com.example.project200.handlers.UniqueFieldException;
import com.example.project200.repositories.SaloonRepository;
import com.example.project200.requestDto.SaloonReqDto;
import com.example.project200.responseDto.SaloonResDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaloonService {

    private final SaloonRepository saloonRepository;
    private final ModelMapper modelMapper;

    public String createSaloon(SaloonReqDto saloonReqDto) {
        // Check if a Saloon with the same name already exists
        Saloon existingSaloon = saloonRepository.findByName(saloonReqDto.getName());
        if (existingSaloon != null) {
            throw new AlreadyExistsException("Saloon with name " + saloonReqDto.getName() + " already exists");
        }
        Saloon saloon = modelMapper.map(saloonReqDto, Saloon.class);

        if (saloonRepository.existsById(saloon.getId())) {
            throw new AlreadyExistsException("Barber with ID " + saloon.getId() + " already exists");
        }
        saloonRepository.save(saloon);

        return "Saloon created successfully";
    }

    public SaloonResDto getSaloonById(Long id) {
        // Retrieve Saloon by ID or throw NotFoundException if not found
        Saloon saloon = saloonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Saloon with ID " + id + " not found"));

        return modelMapper.map(saloon, SaloonResDto.class);
    }

    public List<SaloonResDto> getAllSaloons() {
        return saloonRepository.findAll()
                .stream()
                .map(saloon -> modelMapper.map(saloon, SaloonResDto.class))
                .collect(Collectors.toList());
    }

    public String updateSaloon(Long id, SaloonReqDto saloonReqDto) {
        // Check if the Saloon exists; if not, throw NotFoundException
        Saloon existingSaloon = saloonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Saloon with ID " + id + " does not exist"));

        // Update the fields of the existing Saloon
        existingSaloon.setName(saloonReqDto.getName());
        existingSaloon.setLocation(saloonReqDto.getLocation());
        existingSaloon.setStartTime(saloonReqDto.getStartTime());
        existingSaloon.setEndTime(saloonReqDto.getEndTime());

        saloonRepository.save(existingSaloon);

        return "Saloon updated successfully";
    }

    public String deleteSaloon(Long id) {
        // Check if the Saloon exists; if not, throw NotFoundException
        Saloon saloon = saloonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Saloon with ID " + id + " does not exist"));

        saloonRepository.delete(saloon);
        return "Saloon deleted successfully";
    }

    public List<SaloonResDto> getSaloonsByLocation(String location) {
        return saloonRepository.findByLocation(location)
                .stream()
                .map(saloon -> modelMapper.map(saloon, SaloonResDto.class))
                .collect(Collectors.toList());
    }


}

