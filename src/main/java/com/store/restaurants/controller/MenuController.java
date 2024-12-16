package com.store.restaurants.controller;

import com.store.restaurants.entity.Menu;
import com.store.restaurants.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("restaurant/{restId}/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getMenuitemsFromRestaurant(@PathVariable Integer restId){
        return ResponseEntity.ok(menuService.getItemsFromRestaurant(restId));
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getSpecificMenuItem(@PathVariable Integer restId, @PathVariable Integer menuId){
        return ResponseEntity.ok(menuService.getSpecificItemFromRestaurant(menuId, restId));
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<String> updateMenu(@PathVariable Integer menuId, @ModelAttribute Menu updatedMenu){
        try{
            menuService.updateMenu(menuId, updatedMenu);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Integer menuId){
        try{
            menuService.deleteMenuItem(menuId);
        }catch (NoSuchElementException e){
            ResponseEntity.notFound().build();
        }
        return  ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<String> createMenuItem(@PathVariable Integer restId, @ModelAttribute Menu newMenu){
        if(!restId.equals(newMenu.getRestaurantId())){
            return ResponseEntity.badRequest().body("Cannot create menu outside restaurant");
        }
        menuService.createNewMenu(newMenu);

        return ResponseEntity.accepted().build();
    }
}
