package com.store.restaurants.repository;

import com.store.restaurants.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {
    @Query("SELECT m from Delivery m where m.deliveryPerson = :deliveryPersonId")
    Delivery findDeliveryInfoByDeliveryPerson(Integer deliveryPersonId);
}
