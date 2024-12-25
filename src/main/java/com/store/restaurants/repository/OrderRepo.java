package com.store.restaurants.repository;

import com.store.restaurants.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query("SELECT m from Order m where m.status = :status")
    List<Order> getPendingOrders(@Param("status")Order.Status status);
    @Query("SELECT m from Order m where m.status = :status")
    List<Order> getCompletedOrders(@Param("status")Order.Status status);
}
