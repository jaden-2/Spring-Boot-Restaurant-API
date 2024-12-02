package com.store.restaurants.service;

import com.store.restaurants.entity.Restaurant;
import com.store.restaurants.repository.RestaurantRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;

    public List<Restaurant> getRestaurants(){
        return restaurantRepo.findAll();
    }

    public Restaurant getRestaurantById(Integer id) throws NoSuchElementException {
        return restaurantRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Restaurant does not exist"));
    }

    public void createRestaurant(@NonNull Restaurant restaurant){
        restaurantRepo.save(restaurant);
    }

    public void updateRestaurant(Integer id, @NonNull Restaurant updatedRestaurant) throws NoSuchElementException{
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Cannot update restaurant that does not exist"));

        restaurant.setCity(updatedRestaurant.getCity());
        restaurant.setName(updatedRestaurant.getName());
        restaurant.setStreet(updatedRestaurant.getStreet());
        restaurant.setState(updatedRestaurant.getState());

        restaurantRepo.save(restaurant);
    }

    public void DeleteRestaurant(Integer id) throws NoSuchElementException{
        if(restaurantRepo.existsById(id))
            restaurantRepo.deleteById(id);
        else throw new NoSuchElementException("Cannot delete restaurant that does not exist");
    }
}
