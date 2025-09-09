package com.example.project200.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String number;
    private String mail;
    private String role;


    private Integer isVerified; // Indicates whether the user has verified their email

    private String otp; // Stores the OTP sent to the user

    private LocalDateTime otpExpirationTime;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

