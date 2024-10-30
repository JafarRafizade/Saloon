package com.example.project200.config;

import com.example.project200.entities.Serve;
import com.example.project200.requestDto.ServeReqDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom mapping for ServeReqDto -> ServeRepository
        modelMapper.addMappings(new PropertyMap<ServeReqDto, Serve>() {
            @Override
            protected void configure() {
                // Do NOT map the `id` directly, avoid conflicting mappings
                skip(destination.getId());

                // Map the barberId and saloonId to the corresponding entities
                map().getBarber().setId(source.getBarberId());
                map().getSaloon().setId(source.getSaloonId());
            }
        });

        return modelMapper;
    }
}
