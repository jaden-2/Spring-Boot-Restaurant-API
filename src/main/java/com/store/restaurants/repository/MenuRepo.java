package com.store.restaurants.repository;

import com.store.restaurants.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Integer> {
    @Query("SELECT m from Menu m WHERE m.restaurantId = :restaurantId")
    List<Menu> findByRestaurantId(@Param("restaurantId") Integer restaurantId);

    @Query("SELECT m from Menu m where m.restaurantId = :restaurantId AND m.menuId = :menuId")
    Menu findSpecificItemFromRestaurant(@Param("menuId") Integer menuId, @Param("restaurantId") Integer restaurantId);
    @Query("SELECT m from Menu m where m.restaurantId = :restaurantId AND m.price = :price")
    List<Menu> getMenuByPrice(@Param("price") Double price, @Param("restaurantId") Integer restaurantID);
}

