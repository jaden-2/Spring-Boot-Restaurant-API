package com.store.restaurants.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "restaurant", schema = "mydb")
@Data
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "street", nullable = false)
    @NonNull
    private String Street;

    @Column(name = "city", nullable = false)
    @NonNull
    private String City;

    @Column(name = "state")
    @NonNull
    private String State;
}
