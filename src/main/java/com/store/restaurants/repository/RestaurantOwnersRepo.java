package com.store.restaurants.repository;

import com.store.restaurants.entity.composites.RestaurantOwnerKey;
import com.store.restaurants.entity.RestaurantOwners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantOwnersRepo extends JpaRepository<RestaurantOwners, RestaurantOwnerKey> {

    public List<RestaurantOwners> findById_RestaurantId(Integer restaurantId);

    public List<RestaurantOwners> findById_OwnerId(Integer ownerId);
    @Query("SELECT m.id.restaurantId from RestaurantOwners m where m.id.ownerId = :ownerId")
    public List<Integer> findRestaurantByOwnerId(@Param("ownerId") Integer ownerId);

    @Query("SELECT m.id.ownerId from RestaurantOwners m where m.id.restaurantId = :restId")
    public List<Integer> findOwnerByRestaurantId(@Param("restId") Integer restaurantId);
}
