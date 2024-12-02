package com.store.restaurants.controller;

import com.store.restaurants.entity.Restaurant;
import com.store.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return getRestaurants();
    }
    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurants(){
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer id){
        Restaurant restaurant;
        try{
            restaurant = restaurantService.getRestaurantById(id);
        }
        catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public ResponseEntity<String> createRestaurant(@ModelAttribute Restaurant newRestaurant){
        restaurantService.createRestaurant(newRestaurant);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Integer id, @ModelAttribute Restaurant updatedRestaurant){
        try{
            restaurantService.updateRestaurant(id, updatedRestaurant);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id){
        try{
            restaurantService.DeleteRestaurant(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
