package com.store.restaurants.entity.composites;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOwnerKey implements Serializable {
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;
}
