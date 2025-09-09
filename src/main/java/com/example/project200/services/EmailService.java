package com.example.project200.services;

import com.example.project200.handlers.IllegalArgumentException;
import com.example.project200.util.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;


    public void sendVerificationEmail(String toEmail, String subject, String body) {
        if (!EmailValidator.isValidEmail(toEmail)) {
            throw new IllegalArgumentException("Invalid email address: " + toEmail);
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cafar.rafizade@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return String.valueOf(otp);
    }
}

