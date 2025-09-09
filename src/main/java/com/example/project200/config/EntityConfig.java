package com.example.project200.config;

import com.example.project200.entities.Barber;
import com.example.project200.entities.Customer;
import com.example.project200.entities.MyUser;
import com.example.project200.entities.BarberShop;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfig {
    @Bean
    @ConditionalOnProperty(name = "entity.create-barberShop", havingValue = "true", matchIfMissing = true)
    public BarberShop barberShop() {
        return new BarberShop();  // This bean only registers if entity.create-barberShop is true
    }

    @Bean
    @ConditionalOnProperty(name = "entity.create-myUser", havingValue = "true", matchIfMissing = true)
    public MyUser myUser() {
        return new MyUser();  // This bean only registers if entity.create-barberShop is true
    }

    @Bean
    @ConditionalOnProperty(name = "entity.create-barber", havingValue = "true")
    public Barber barber() {
        return new Barber();  // Conditionally registers EntityA as a bean
    }

    @Bean
    @ConditionalOnProperty(name = "entity.create-customer", havingValue = "true", matchIfMissing = true)
    public Customer customer() {
        return new Customer();  // This bean only registers if entity.create-barberShop is true
    }
}
