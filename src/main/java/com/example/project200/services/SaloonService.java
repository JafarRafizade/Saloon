package com.example.project200.services;

import com.example.project200.entities.Saloon;
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
        Saloon saloon = modelMapper.map(saloonReqDto, Saloon.class);
        Saloon saloonSaved = saloonRepository.findByName(saloon.getName());
        if (saloonSaved != null) {
            return "Saloon already exists";
        }
        else {
            saloonRepository.save(saloon);
            return "Saloon created";
        }
    }
    public SaloonResDto getSaloonById(Long id) {
        Saloon saloon = saloonRepository.findById(id).orElse(null);
        return modelMapper.map(saloon, SaloonResDto.class);
    }
    public List<SaloonResDto> getAllSaloons() {
        return saloonRepository.findAll()
                .stream()
                .map(saloon -> modelMapper.map(saloon, SaloonResDto.class))
                .collect(Collectors.toList());
    }
    public String updateSaloon(Long id, SaloonReqDto saloonReqDto) {

        Saloon existedSaloon = saloonRepository.findById(id).orElse(null);
        if (existedSaloon == null) {
            return "Saloon does not exist";
        } else {
            existedSaloon.setName(saloonReqDto.getName());
            existedSaloon.setLocation(saloonReqDto.getLocation());
            existedSaloon.setStartTime(saloonReqDto.getStartTime());
            existedSaloon.setEndTime(saloonReqDto.getEndTime());
            saloonRepository.save(existedSaloon);
            return "Saloon updated";
        }
    }
    public String deleteSaloon(Long id) {
        Saloon saloon = saloonRepository.findById(id).orElse(null);
        if (saloon == null) {
            return "Saloon does not exist";
        }
        else {
            saloonRepository.delete(saloon);
            return "Saloon deleted";
        }
    }
    public List<SaloonResDto> getSaloonsByLocation(String location) {
        return saloonRepository.findByLocation(location)
                .stream()
                .map(saloon -> modelMapper.map(saloon, SaloonResDto.class))
                .collect(Collectors.toList());
    }




}

