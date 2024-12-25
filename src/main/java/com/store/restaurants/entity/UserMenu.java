package com.store.restaurants.entity;

import com.store.restaurants.entity.composites.UserMenuKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_menu", schema = "mydb")
@Data
@NoArgsConstructor
public class UserMenu {
    @EmbeddedId
    private UserMenuKey key;
    private Integer quantity;
}
