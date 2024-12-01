package com.store.restaurants.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "user", schema = "mydb")
@Data
@NoArgsConstructor
public class User {
    public static enum Role {DEFAULT, OWNER, CUSTOMER, DELIVERY}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private Integer user_id;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "role")
    @NonNull
    private Role role;

    @Column(name = "first_name", nullable = false)
    @NonNull
    private String first_name;

    @Column(name = "last_name")
    @NonNull
    private String last_name;


}
