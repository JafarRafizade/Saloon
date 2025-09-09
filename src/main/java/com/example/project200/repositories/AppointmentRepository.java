package com.example.project200.repositories;

import com.example.project200.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = "SELECT * FROM appointment " +
            "WHERE customer_id = :customerId " +
            "AND appointment_date >:appointmentDate " +
            "AND status IN ('SUCCESS', 'REJECTED')",
            nativeQuery = true)
    Appointment findAppointmentsByCustomerIdAndDateAndStatus(
            @Param("customerId") Long customerId,
            @Param("appointmentDate") LocalDateTime appointmentDate);

}
