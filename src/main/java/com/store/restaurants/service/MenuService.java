package com.store.restaurants.service;

import com.store.restaurants.entity.Menu;
import com.store.restaurants.repository.MenuRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MenuService {
    @Autowired
    private MenuRepo menuRepo;

    public List<Menu> getItems(){
        return menuRepo.findAll();
    }

    public List<Menu> getItemsFromRestaurant(Integer restaurantId){
        return menuRepo.findByRestaurantId(restaurantId);
    }

    public Menu getSpecificItemFromRestaurant(Integer menuId, Integer restaurantId){
        return menuRepo.findSpecificItemFromRestaurant(menuId, restaurantId);
    }

    public List<Menu> getByPrice(Double price, Integer restaurantId){
        return menuRepo.getMenuByPrice(price, restaurantId);
    }

    public void createNewMenu(@NonNull Menu newMenu){
        menuRepo.save(newMenu);
    }

    public void updateMenu(Integer menuId, Menu updatedMenu) throws NoSuchElementException{
        Menu menu = menuRepo.findById(menuId).orElseThrow(()-> new NoSuchElementException("Cannot update menu item that does not exist"));

        menu.setName(updatedMenu.getName());
        menu.setPrice(updatedMenu.getPrice());
        menu.setRestaurantId(updatedMenu.getRestaurantId());

        menuRepo.save(menu);
    }

    public void deleteMenuItem(Integer menuId)throws NoSuchElementException{
        if(menuRepo.existsById(menuId)) menuRepo.deleteById(menuId);
        else throw new NoSuchElementException("Cannot delete menu item that does not exist");
    }

}
