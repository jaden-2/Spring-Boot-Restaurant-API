package com.store.restaurants.service;

import com.store.restaurants.entity.Menu;
import com.store.restaurants.repository.MenuRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.InputMismatchException;
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

    public Menu getSpecificItemFromRestaurant(Integer menuId, Integer restaurantId) throws NoSuchElementException{
        Menu menuItem;

        menuItem = menuRepo.findSpecificItemFromRestaurant(menuId, restaurantId);
        return menuItem;
    }

    public List<Menu> getAllById(List<Integer> menuIds){
        return menuRepo.findAllById(menuIds);
    }

    public List<Menu> getByPrice(Double price, Integer restaurantId){
        return menuRepo.getMenuByPrice(price, restaurantId);
    }
    /*
    * Menu items for all restaurant exist in one table with a primary key menuId
    * the restaurant id should not be required when updating and creating menu items
    * These functionalities are for administrative use only
    *However, to ensure change to a  restaurant by its admin, the restaurant id should be required for update and delete actions*/
    public void createNewMenu(@NonNull Menu newMenu, Integer restId) throws InputMismatchException{
        if(restId.equals(newMenu.getRestaurantId()))
            menuRepo.save(newMenu);
        else throw new InputMismatchException("Cannot create new menu for another restaurant");
    }

    public void updateMenu(Integer restId, Integer menuId, Menu updatedMenu) throws NoSuchElementException{

        Menu menu;
        if(menuRepo.existsByRestaurantIdAndMenuId(restId, menuId))
            menu = this.getSpecificItemFromRestaurant(menuId, restId);
        else throw new NoSuchElementException("Cannot update menu item that does not exist or restaurant");

        menu.setName(updatedMenu.getName());
        menu.setPrice(updatedMenu.getPrice());
        menu.setRestaurantId(updatedMenu.getRestaurantId());

        menuRepo.save(menu);
    }

    public void deleteMenuItem(Integer restId, Integer menuId)throws NoSuchElementException{
        boolean exists = menuRepo.existsByRestaurantIdAndMenuId(restId, menuId);
        if(exists){
            menuRepo.deleteById(menuId);
        }
        else throw new NoSuchElementException("Cannot delete menu item that does not exist or belong to restaurant");
    }

    public boolean menuExistsById(Integer menuId){
        return menuRepo.existsById(menuId);
    }
}
