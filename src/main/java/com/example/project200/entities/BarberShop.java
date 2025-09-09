package com.example.project200.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class BarberShop {
    @Id
    private Long id;
    private String companyName;
    private String branchName;
    private String location;
    private LocalTime startTime;
    private LocalTime endTime;
    @OneToMany(mappedBy = "barberShop",cascade = CascadeType.ALL)
    private Set<Barber> barberList=new HashSet<>();
    @OneToMany(mappedBy = "barberShop",cascade = CascadeType.ALL)
    private List<BarberServices> barberServices;


}
