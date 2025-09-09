package com.example.project200.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "barber_seq")
    @SequenceGenerator(name = "barber_seq", sequenceName = "barber_id_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)

    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private Integer onlineStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "barberShop_id")
    @JsonIgnore
    private BarberShop barberShop;
    @ElementCollection
    @JsonIgnore
    private List<Long> serviceId;
    @OneToOne(mappedBy = "barber", cascade = CascadeType.ALL)
    private MyUser myUser;




}
