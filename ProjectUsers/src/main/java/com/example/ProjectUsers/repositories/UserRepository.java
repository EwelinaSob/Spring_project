package com.example.ProjectUsers.repositories;


import com.example.ProjectUsers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByNameAndSurname(String name, String surname);

}
