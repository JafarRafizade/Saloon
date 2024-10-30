package com.example.project200.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Saloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "saloon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Barber> barbers;

    @OneToMany(mappedBy = "saloon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Serve> barberServices;

}
