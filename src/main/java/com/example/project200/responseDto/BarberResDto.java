package com.example.project200.responseDto;

import com.example.project200.entities.BarberServices;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class BarberResDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer onlineStatus;
    private List<Long> blockList;
    private Set<BarberServices> barberServices;

}
