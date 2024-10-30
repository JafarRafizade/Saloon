package com.example.project200.repositories;

import com.example.project200.entities.Barber;
import com.example.project200.entities.Saloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
    List<Barber> findBarbersBySaloon(Saloon saloon);
}
