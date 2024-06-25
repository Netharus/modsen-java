package com.example.springPizza.repositories;

import com.example.springPizza.database.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByLogin(String login);

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByLogin(String login);

}
