package com.example.project200.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Notification {
    @Id
    private Long id;
    private Long barberId;
    private Long customerId;
    private LocalDateTime time;
    private Status status;
    @ElementCollection
    private Set<Long> appointmentsId;
}
