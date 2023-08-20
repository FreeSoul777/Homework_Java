package com.learning.processing.test;

import com.learning.processing.ob.Account;
import com.learning.processing.ob.InMemoryBase;
import com.learning.processing.ob.User;
import com.learning.processing.inter.AccountService;
import com.learning.processing.inter.AccountServiceImpl;
import com.learning.processing.inter.UserServiceImpl;
import com.learning.processing.inter.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceTest {

    private UserService userService;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        accountService = new AccountServiceImpl();
    }

    @Test
    void isUserRegisterCorrectly(){
        //Проверка, что пользователь регистрируется в in-memory базу
        User user = new User("John", "Doe", "johndoe@mail.com", "password");
        userService.register(user);
        assertTrue(userService.isUserRegistered(user.getId()));
    }

    @Test
    void isUserAuthCorrectly(){
        //Проверка, что пользователь успешно авторизовался
        User user = new User("John", "Doe", "johndoe@mail.com", "password");
        userService.register(user);
        assertTrue(userService.isUserAuthenticated(user.getEmail(), user.getPassword()));
    }

    @Test
    void userIsAbleToCreateAccount(){
        //Проверка, что пользователь может создавать счёт
        User user = new User("John", "Doe", "johndoe@mail.com", "password");
        userService.register(user);
        int accountID = accountService.createAccount(user, 250);
        Optional<Account> optionalAccount = accountService.getAccountById(user, accountID);
        assertTrue(optionalAccount.isPresent());
    }

    @Test
    void userIsAbleToCloseAccount(){
        //Проверка, что пользователь может закрыть счет
        User user = new User("John", "Doe", "johndoe@mail.com", "password");
        userService.register(user);
        int accountID = accountService.createAccount(user, 250); // счет создался

        accountService.closeAccount(user, accountID ); // счет закрылся
        Optional<Account> optionalAccount = accountService.getAccountById(user, accountID);
        assertFalse(optionalAccount.isPresent());
    }
}
