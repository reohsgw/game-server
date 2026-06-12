//HASEGAWA REO
package com.team7.gameserver.entity;

import jakarta.persistence.*;

//represents a player account - maps to the "users" table in the database
@Entity
@Table(name = "users")
public class User {

    @Id
    private String id; // players login id (used as primary key)

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
