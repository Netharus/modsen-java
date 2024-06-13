package com.example.springPizza.repositories;

import com.example.springPizza.database.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
