package com.store.restaurants.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "Order", schema ="mydb")
@Data
@NoArgsConstructor
public class Order {
    public enum  Status {PENDING, COMPLETED};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "custumer_id")
    @NonNull
    private Integer CustumerId;

    @Column(name = "total_price")
    @NonNull
    private Double totalPrice;

    @Column(name = "status")
    @NonNull
    private Status status;

    @Column(name = "restaurant_id")
    @NonNull
    private Integer restaurantId;


}
