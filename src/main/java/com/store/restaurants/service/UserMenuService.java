package com.store.restaurants.service;

import com.store.restaurants.entity.Menu;
import com.store.restaurants.entity.UserMenu;
import com.store.restaurants.entity.composites.UserMenuKey;
import com.store.restaurants.repository.UserMenuRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserMenuService {

    @Autowired
    UserMenuRepo userMenuRepo;
    @Autowired
    MenuService menuService;
    @Autowired
    UserService userService;
    public List<Menu> getUserMenu(Integer userId) throws NoSuchElementException{
        boolean exists = userMenuRepo.existsByKey_UserId(userId);

        if (exists){
            List<Integer> userMenus = userMenuRepo.findMenuByUserId(userId);
            return menuService.getAllById(userMenus);
        }
        throw new NoSuchElementException("User does not has not selected items from menu");
    }


    public void addToUserMenu(@NonNull UserMenu costumerItem) throws NoSuchElementException{
        boolean menuExists = menuService.menuExistsById(costumerItem.getKey().getMenuId());
        boolean costumerExists = userService.userExistsById(costumerItem.getKey().getUserId());
        boolean userMenuExists = userMenuRepo.existsById(costumerItem.getKey());

        // item must be a valid menu item, costumer must be a valid user, and item must not exist in relation
        if(menuExists && costumerExists){
            if(!userMenuExists) {
                // Item has not been added by user
                userMenuRepo.save(costumerItem);
            } else {
                // User has added this item, update it
                this.updateUserMenuItem(costumerItem);
            }
        }else throw new NoSuchElementException("Menu item or user is invalid");
    }

    public void updateUserMenuItem(@NonNull UserMenu updatedMenuItem){
        if(userMenuRepo.existsById(updatedMenuItem.getKey())){
            // Existing user menu item
            UserMenu userMenuItem = userMenuRepo.findById(updatedMenuItem.getKey()).get();

            // Existing User menu item key
            UserMenuKey userMenuKey = userMenuItem.getKey();

            // Explicitly setting user menu id to ensure costumer id is not updated
            userMenuKey.setMenuId(updatedMenuItem.getKey().getMenuId());
            userMenuItem.setKey(userMenuKey);

            // Updating quantity
            userMenuItem.setQuantity(updatedMenuItem.getQuantity());

            userMenuRepo.save(userMenuItem);

        }else throw new NoSuchElementException("Cannot edit menu item that does not exist");
    }

    public void deleteUserMenuItem(UserMenuKey key){
        if(userMenuRepo.existsById(key)) userMenuRepo.deleteById(key);
        else throw new NoSuchElementException("Cannot delete item that does not exist");
    }

    public void deleteAllUserMenuItem(Integer costumerId){
        if(userMenuRepo.existsByKey_UserId(costumerId)){
            userMenuRepo.deleteByKey_UserId(costumerId);
        }else throw new NoSuchElementException("Cannot delete user items when user does not have items");
    }
}
