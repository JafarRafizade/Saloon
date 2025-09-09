package com.example.project200.repositories;

import com.example.project200.entities.Barber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
    Optional<List<Barber>> findByFirstName(String firstName);
    Optional<List<Barber>> findByLastName(String lastName);


    @Query("SELECT b FROM Barber b WHERE " +
            "(LOWER(b.firstName) LIKE LOWER(CONCAT('%', :engine, '%')) OR " +
            "LOWER(b.lastName) LIKE LOWER(CONCAT('%', :engine, '%')))")
    Page<Barber> searchByName(@Param("engine") String engine, Pageable pageable);

    @Query(value = "SELECT * FROM barber WHERE online_status = 1", nativeQuery = true)
    Page<Barber> findBarbersByOnlineStatus(Pageable pageable);


    Optional<List<Barber>> findBarberByBarberShopId(Long barberShopId);


}
