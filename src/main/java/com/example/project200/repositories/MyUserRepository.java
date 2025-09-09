package com.example.project200.repositories;

import com.example.project200.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    boolean existsByUsername(String username);
    boolean existsByNumber(String number);
    MyUser findMyUserByBarberId(Long id);
    void deleteByBarberId(Long id);

    boolean existsByMail(String mail);

}
