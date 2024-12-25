package com.store.restaurants.controller;

import com.store.restaurants.entity.Restaurant;
import com.store.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam(value = "restId", required = false) Integer restId){
        if (restId == null)
            return ResponseEntity.ok(restaurantService.getRestaurants());

        // Return the restaurant requested for
        Restaurant restaurant;
        List<Restaurant> restaurantList = new ArrayList<>();
        try{
            restaurant = restaurantService.getRestaurantById(restId);
        }
        catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        restaurantList.add(restaurant);
        return ResponseEntity.ok(restaurantList);
    }

    @PostMapping
    public ResponseEntity<String> createRestaurant(@ModelAttribute Restaurant newRestaurant){
        restaurantService.createRestaurant(newRestaurant);
        return ResponseEntity.ok("Created");
    }

    @PutMapping
    public ResponseEntity<String> updateRestaurant(@RequestParam("id") Integer id, @ModelAttribute Restaurant updatedRestaurant){
        try{
            restaurantService.updateRestaurant(id, updatedRestaurant);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRestaurant(@RequestParam("id") Integer id){
        try{
            restaurantService.DeleteRestaurant(id);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
