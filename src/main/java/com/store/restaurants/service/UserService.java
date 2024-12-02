package com.store.restaurants.service;

import com.store.restaurants.entity.User;
import com.store.restaurants.repository.UserRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public void createNewUser(@NonNull User newUser) {
        userRepository.save(newUser);
    }

    public User getUserbyId(Integer id) throws NoSuchElementException{
        User user;
        user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User does not exist"));
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void updateUser(Integer id, @NonNull User updateUser) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("Cannot update user that does not exist"));
        user.setEmail(updateUser.getEmail());
        user.setRole(updateUser.getRole());
        user.setPassword(updateUser.getPassword());
        user.setFirst_name(updateUser.getFirst_name());
        user.setLast_name(updateUser.getLast_name());

        userRepository.save(user);
    }

    public void deleteUser(Integer id) throws NoSuchElementException{
        if(userRepository.existsById(id))
            userRepository.deleteById(id);
        else throw new NoSuchElementException("User does not exist");

    }
}
