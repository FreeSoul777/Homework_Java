package com.learning.processing.test;

import com.learning.processing.inter.AccountServiceImpl;
import com.learning.processing.ob.Account;
import com.learning.processing.ob.InMemoryBase;
import com.learning.processing.ob.User;
import com.learning.processing.inter.ProcessingServiceImpl;
import com.learning.processing.inter.UserServiceImpl;
import com.learning.processing.inter.AccountService;
import com.learning.processing.inter.ProcessingService;
import com.learning.processing.inter.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingServiceTest {

    private ProcessingService processingService;
    private AccountService accountService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        accountService = new AccountServiceImpl();
        processingService = new ProcessingServiceImpl(accountService, userService);
    }

    @Test
    void transactionsBetweenAccountsIsValid(){
        //проверка работоспособности транзакции между счетами разных пользователей

        // Создаем двух пользователей
        User user1 = new User("John", "Doe", "john.doe@example.com", "password");
        userService.register(user1);
        User user2 = new User("Jane", "Doe", "jane.doe@example.com", "password");
        userService.register(user2);

        // Создаем счета для пользователей
        int accountID_1 = accountService.createAccount(user1, 0);
        Account account1 = accountService.getAccountById(user1, accountID_1).get();
        account1.setBalance(100.0);

        int accountID_2 = accountService.createAccount(user2, 0);
        Account account2 = accountService.getAccountById(user2, accountID_2).get();
        account2.setBalance(50.0);

        // Переводим деньги между счетами
        assertTrue(processingService.transfer(account1, account2, 25.0));


        // Проверяем, что балансы изменились правильно
        assertEquals(75.0, account1.getBalance());
        assertEquals(75.0, account2.getBalance());
    }

    @Test
    void transactionsBetweenAccountsOfOneUserIsValid(){
        //проверка работоспособности транзакции между счетами одного пользователя

        // Создаем пользователя
        User user = new User("John", "Doe", "john.doe@example.com", "password");
        userService.register(user);

        // Создаем два счета для пользователя
        int accountID_1 = accountService.createAccount(user, 0);
        Account account1 = accountService.getAccountById(user, accountID_1).get();
        account1.setBalance(100.0);
        int accountID_2 = accountService.createAccount(user, 0);
        Account account2 = accountService.getAccountById(user, accountID_2).get();
        account2.setBalance(50.0);

        // Переводим деньги между счетами
        assertTrue(processingService.transfer(account1, account2, 25.0));

        // Проверяем, что балансы изменились правильно
        assertEquals(75.0, account1.getBalance());
        assertEquals(75.0, account2.getBalance());
    }

    @Test
    void transactionRollbackByError(){
        //проверка, что транзакция откатывает изменения при ошибке передачи

        // Создаем пользователя
        User user = new User("John", "Doe", "john.doe@example.com", "password");
        userService.register(user);

        // Создаем два счета для пользователя
        int accountID_1 = accountService.createAccount(user, 0);
        Account account1 = accountService.getAccountById(user, accountID_1).get();
        account1.setBalance(100.0);
        int accountID_2 = accountService.createAccount(user, 0);
        Account account2 = accountService.getAccountById(user, accountID_2).get();
        account2.setBalance(50.0);

        Account error = new Account(new User("Sam", "Sam", "sam@example.com", "password"),
                100, 0);

        // Переводим деньги между счетами, но передаем неверный ID счета
        assertFalse(processingService.transfer(account1, error, 25.0));

        // Проверяем, что балансы не изменились
        assertEquals(100.0, account1.getBalance());
        assertEquals(50.0, account2.getBalance());
    }

    @Test
    void transactionNotPermitIfNegativeDebt(){
        //проверка, что транзакция не осуществляется при отрицательном балансе счёта - отправителя
        // Создаем пользователя
        User user = new User("John", "Doe", "john.doe@example.com", "password");
        userService.register(user);

        // Создаем два счета для пользователя
        int accountID_1 = accountService.createAccount(user, 0);
        Account account1 = accountService.getAccountById(user, accountID_1).get();
        account1.setBalance(-100.0);

        int accountID_2 = accountService.createAccount(user, 0);
        Account account2 = accountService.getAccountById(user, accountID_2).get();
        account2.setBalance(50.0);

        // Переводим больше денег, чем есть на счету
        assertFalse(processingService.transfer(account1, account2, 25.0));

        // Проверяем, что балансы не изменились
        assertEquals(-100.0, account1.getBalance());
        assertEquals(50.0, account2.getBalance());
    }
}
