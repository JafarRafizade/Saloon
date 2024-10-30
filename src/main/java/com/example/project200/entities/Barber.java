package com.example.project200.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private Long phoneNumber;
    private String email;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "saloon_id")
    private Saloon saloon;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.PERSIST)
    private List<Serve> barberServices;


    private int onlineStatus;


}
