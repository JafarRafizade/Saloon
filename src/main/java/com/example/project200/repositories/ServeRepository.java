package com.example.project200.repositories;

import com.example.project200.entities.Serve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServeRepository extends JpaRepository<com.example.project200.entities.Serve, Long> {
    Serve findByName(String name);
    List<Serve> findAllById(Iterable<Long> ids);
}
