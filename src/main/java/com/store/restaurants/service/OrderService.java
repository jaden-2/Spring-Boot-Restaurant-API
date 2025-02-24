package com.store.restaurants.service;

import com.store.restaurants.entity.Order;
import com.store.restaurants.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


// Make corrections to the order service, make use of menu service as a reference guide.
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getALlExistingOrders(){
        return orderRepo.findAll();
    }

    public Order getSpecificOrder(Integer orderId) throws NoSuchElementException{
        return orderRepo.findById(orderId).orElseThrow(()-> new NoSuchElementException("Cannot get order that does not exist"));
    }

    public List<Order> getPendingOrders(){
        return orderRepo.getPendingOrders(Order.Status.PENDING);
    }

    public List<Order> getCompletedOrder(){
        return orderRepo.getCompletedOrders(Order.Status.COMPLETED);
    }

    public void createOrder(Order newOrder){
        orderRepo.save(newOrder);
    }

    public void updateOrder(Integer orderId, Order updatedOrder) throws NoSuchElementException{
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new NoSuchElementException("Cannot update order that does not exist"));
        order.setStatus(updatedOrder.getStatus());
        order.setTotalPrice(updatedOrder.getTotalPrice());
        orderRepo.save(order);
    }

    public void deleteOrder(Integer orderId) throws NoSuchElementException{
        if (orderRepo.existsById(orderId)) orderRepo.deleteById(orderId);
        else throw new NoSuchElementException("Cannot delete order that does not exist");
    }
}
