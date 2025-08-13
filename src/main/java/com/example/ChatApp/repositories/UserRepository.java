package com.example.ChatApp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ChatApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;





public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
