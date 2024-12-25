package com.store.restaurants.entity;

import com.store.restaurants.entity.composites.RestaurantOwnerKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Restaurant_owners", schema = "mydb")
@Data
@NoArgsConstructor
public class RestaurantOwners {
    @EmbeddedId
    private RestaurantOwnerKey id;
}
