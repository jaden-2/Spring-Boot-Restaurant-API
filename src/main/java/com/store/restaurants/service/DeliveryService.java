package com.store.restaurants.service;

import com.store.restaurants.entity.Delivery;
import com.store.restaurants.entity.User;
import com.store.restaurants.repository.DeliveryRepo;
import com.store.restaurants.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRoleInfoException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Delivery> getAllDelivery(){
        return deliveryRepo.findAll();
    }

    public Delivery getDeliveryById(Integer deliveryId) throws NoSuchElementException {
        Delivery delivery = deliveryRepo.findById(deliveryId).orElseThrow(()-> new NoSuchElementException("Cannot get delivery that does not exist"));
        return delivery;
    }

    public Delivery getDeliveryByPerson(Integer deliveryPersonId) throws NoSuchElementException{
        Delivery delivery;
        try {
            delivery = deliveryRepo.findDeliveryInfoByDeliveryPerson(deliveryPersonId);
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Cannot get delivery information with deliveryPersonId that does not exist");
        }
        return delivery;
    }

    public void createDeliveryInformation(Delivery delivery){
        deliveryRepo.save(delivery);
    }

    public void deleteDeliveryById(Integer deliveryId) throws NoSuchElementException{
        if (deliveryRepo.existsById(deliveryId)) deliveryRepo.deleteById(deliveryId);
        else throw new NoSuchElementException("Cannot delete delivery information that does not exist");
    }

    public void updateDeliveryInformation(Integer deliveryId, Delivery updateDelivery) throws InvalidRoleInfoException, NoSuchElementException {
        Delivery delivery = deliveryRepo.findById(deliveryId).orElseThrow(()-> new NoSuchElementException("Cannot update delivery information that does not exist"));

        User deliveryPerson = userRepo.findById(updateDelivery.getDeliveryPerson()).orElseThrow(()-> new NoSuchElementException("Cannot update delivery information with delivery person that does not exist"));

        // Checks the role of the updated delivery person to ensure that it's of DELIVERY
        if(deliveryPerson.getRole() != User.Role.DELIVERY) throw new InvalidRoleInfoException("Delivery must be done by a user with role DELIVERY");

        delivery.setDeliveryPerson(updateDelivery.getDeliveryPerson());

    }
}
