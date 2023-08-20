package com.learning.processing.inter;

import com.learning.processing.ob.InMemoryBase;
import com.learning.processing.ob.User;

public interface UserService {
    void register(User user);
    boolean isUserRegistered(int userId);
    boolean isUserAuthenticated(String email, String password);
}