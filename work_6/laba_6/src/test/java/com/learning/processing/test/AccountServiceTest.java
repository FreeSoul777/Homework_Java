package com.learning.processing.test;

import com.learning.processing.inter.AccountServiceImpl;
import com.learning.processing.ob.Account;
import com.learning.processing.ob.InMemoryBase;
import com.learning.processing.ob.User;
import com.learning.processing.inter.UserServiceImpl;
import com.learning.processing.inter.AccountService;
import com.learning.processing.inter.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService accountService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        accountService = new AccountServiceImpl();
    }

    @Test
    void createdAccountIsBelongToUser(){
        //проверяет, что при создании счёта он действительно принадлежит нужному (конкретному) пользователю
        User user = new User("John", "Doe", "johndoe@mail.com", "password");
        userService.register(user);
        int accountID = accountService.createAccount(user, 250);
        assertTrue(accountService.getAccountById(user, accountID).isPresent());
    }

    @Test
    void nonAuthorizedUserHasNoAccessToAccount(){
        //проверяет, что неавторизованный пользователь или другой юзер не может получить доступ к чужому счёту
        User user1 = new User("John", "Doe", "johndoe@mail.com", "password");
        User user2 = new User("Jane", "Doe", "janedoe@mail.com", "password");
        userService.register(user1);
        userService.register(user2);
        int accountID_1 = accountService.createAccount(user1, 250);
        assertFalse(accountService.getAccountById(user2, accountID_1).isPresent());
    }

    @Test
    void accountIsBelongToUser(){
        //проверка, что поле userId класса Account не пустое (то есть создаваемому счёт принадлежит какому-либо пользователю)
        User user = new User("John", "Doe", "johndoe@mail.com", "password");
        userService.register(user);
        int accountID = accountService.createAccount(user, 250);
        Account account = user.getAccounts().get(accountID);
        assertNotNull(account.getUserId());
    }

}
