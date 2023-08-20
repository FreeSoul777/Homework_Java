package com.learning.processing.ob;

import java.util.HashMap;

public class InMemoryBase {
    private final HashMap<Integer, User> users;
    public InMemoryBase(HashMap<Integer, User> users){
        this.users = users;
    }
    public void addUser(User user){
        users.put(user.getId(), user);
    }
    public HashMap<Integer, User> getUsers() {
        return users;
    }

}
