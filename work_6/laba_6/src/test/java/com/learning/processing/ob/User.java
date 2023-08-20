package com.learning.processing.ob;

import java.util.HashMap;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    HashMap<Integer, Account> accounts;

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        accounts = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }
}