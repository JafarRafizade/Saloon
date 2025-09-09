package com.example.project200.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    private Long id;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private MyUser myUser;
}

