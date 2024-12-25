package com.store.restaurants.controller;

import com.store.restaurants.entity.Menu;
import com.store.restaurants.entity.Message;
import com.store.restaurants.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("restaurant/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getMenuitemsFromRestaurant(@RequestParam("storeId") Integer restId, @RequestParam(value = "menuId", required = false) Integer menuId){
        if (menuId == null)
            return ResponseEntity.ok(menuService.getItemsFromRestaurant(restId));
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menuService.getSpecificItemFromRestaurant(menuId, restId));

        return ResponseEntity.ok(menuList);
    }

    @PutMapping
    public ResponseEntity<String> updateMenu(@RequestParam("storeId") Integer restId, @RequestParam("menuId") Integer menuId, @ModelAttribute Menu updatedMenu){
        try{
            menuService.updateMenu(restId, menuId, updatedMenu);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(String.valueOf(new Message(e.getLocalizedMessage())));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Message> deleteMenuItem(@RequestParam("storeId") Integer restId, @RequestParam("menuId") Integer menuId){
        try{
            menuService.deleteMenuItem(restId, menuId);
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(new Message(e.getLocalizedMessage()));
        }
        return  ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Message> createMenuItem(@RequestParam("storeId") Integer restId, @ModelAttribute Menu newMenu){
        try{
            menuService.createNewMenu(newMenu, restId);
        }catch (InputMismatchException e){
            return ResponseEntity.badRequest().body(new Message(e.getLocalizedMessage()));
        }

        return ResponseEntity.accepted().build();
    }
}
