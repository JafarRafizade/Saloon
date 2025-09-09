package com.example.project200.repositories;

import com.example.project200.entities.BarberServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarberServiceRepository extends JpaRepository<BarberServices, Long> {
    Optional<List<BarberServices>> findByBarberShopId(Long id);

}
