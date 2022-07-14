package com.example.ProjectUsers;

import com.example.ProjectUsers.model.User;
import com.example.ProjectUsers.model.UserManagerDTO;
import com.example.ProjectUsers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/users")
//    List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    @GetMapping("/users/{id}")
    UserManagerDTO getUserByID(@PathVariable Integer id) {

        User found = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return createUserDTO(found);

    }
    public  UserManagerDTO createUserDTO(User model) {

        String managerName;
        String managerSurname;
        if(model.getManager() == null) {
            managerName = "none";
            managerSurname = "none";
        }
        else{
            User userManager = userRepository.findById(model.getManager()).orElseThrow(() -> new  UserNotFoundException(model.getManager()));
            managerName= userManager.getName();
            managerSurname = userManager.getSurname();
        }
        UserManagerDTO user = new UserManagerDTO(model.getName(),model.getSurname(),
                model.getJob(),managerName,managerSurname);
        return user;
    }

    @PostMapping("/users")
    ResponseEntity<?> addUser(@RequestBody User newUser) {
        if(userRepository.existsByNameAndSurname(newUser.getName(),newUser.getSurname())) {
            return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);}
        else if(newUser.getName()==null || newUser.getSurname()== null || newUser.getJob() == null) throw new FieldNotEmptyException();
        else {
            return new ResponseEntity<User>(userRepository.save(newUser),HttpStatus.CREATED);
        }}

}