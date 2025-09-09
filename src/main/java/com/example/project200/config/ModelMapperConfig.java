package com.example.project200.config;

import com.example.project200.entities.Barber;
import com.example.project200.requestDto.BarberUpdateReqDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        var mapper=new ModelMapper();
        mapper.createTypeMap(BarberUpdateReqDto.class, Barber.class)
                .addMapping(src->null,Barber::setId);
        return mapper;
    }
}

