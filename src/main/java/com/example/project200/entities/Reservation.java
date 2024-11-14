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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "barber_id")
    private Barber barber;


    @ElementCollection
    @CollectionTable(name = "reservation_serve_ids", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "serve_id")
    private List<Long> serveIds;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    private Status status;
}
