package com.store.restaurants.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "menu", schema = "mydb")
@Data
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "restaurant_id")
    @NonNull
    private Integer restaurantId;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "price")
    @NonNull
    private Double price;
}
