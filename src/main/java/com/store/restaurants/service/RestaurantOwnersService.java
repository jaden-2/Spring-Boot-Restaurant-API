package com.store.restaurants.service;

import com.store.restaurants.repository.RestaurantOwnersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantOwnersService {
    @Autowired
    RestaurantOwnersRepo restaurantOwnersRepo;

    public List<Integer> getRestaurantByOwner(Integer ownerId){
        return restaurantOwnersRepo.findRestaurantByOwnerId(ownerId);
    }
}
