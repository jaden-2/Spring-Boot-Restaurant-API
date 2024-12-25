package com.store.restaurants.controller;

import com.store.restaurants.entity.User;
import com.store.restaurants.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "id", required = false) Integer id){
        // Returns a list of all users
        if(id == null)
            return ResponseEntity.ok(userService.getAllUsers());

        User user;
        List<User> users = new ArrayList<>();
        // In case user does not exist
        try{
            user = userService.getUserbyId(id);
        } catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(null);
        }
        users.add(user);

        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@ModelAttribute User newUser){
        userService.createNewUser(newUser);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestParam("id") Integer id, @ModelAttribute User updateUser){
        try{
            userService.updateUser(id, updateUser);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam("id") Integer id){
        try{
            userService.deleteUser(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
