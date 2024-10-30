package com.example.project200.repositories;

import com.example.project200.entities.Saloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaloonRepository extends JpaRepository<Saloon, Long> {
    Saloon findByName(String name);
    List<Saloon> findByLocation(String location);
}
