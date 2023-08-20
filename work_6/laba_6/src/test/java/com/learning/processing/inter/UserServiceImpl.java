package com.learning.processing.inter;

import com.learning.processing.ob.InMemoryBase;
import com.learning.processing.ob.User;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private int lastUserId = 0;
    private static InMemoryBase base;

    public UserServiceImpl() {
        this.base = new InMemoryBase(new HashMap<Integer, User>());
    }

    @Override
    public void register(User user) {
        // регистрирует пользователя, если не было такой почты
        if (base.getUsers().values().stream().anyMatch(us -> us.getEmail().equals(user.getEmail()))) {
            return;
        }
        user.setId(++lastUserId);
        base.getUsers().put(user.getId(), user);
    }

    @Override
    public boolean isUserRegistered(int userId) {
        // проверяет, что такой пользователь существует
        return base.getUsers().values().stream()
                .anyMatch(user -> user.getId() == userId);
    }

    @Override
    public boolean isUserAuthenticated(String email, String password) {
        // проверяет, что пользователь успешно авторизовался
        return base.getUsers().values().stream()
                .anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }
}
