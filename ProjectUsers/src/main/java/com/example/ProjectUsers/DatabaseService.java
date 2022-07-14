package com.example.ProjectUsers;


import com.example.ProjectUsers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseService {

    @Autowired
    private UserRepository userRepository;



}
