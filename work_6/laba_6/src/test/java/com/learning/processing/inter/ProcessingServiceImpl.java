package com.learning.processing.inter;

import com.learning.processing.ob.Account;
import com.learning.processing.ob.InMemoryBase;

import java.util.Optional;

public class ProcessingServiceImpl implements ProcessingService {

    private final AccountService accountService;
    private final UserService userService;

    public ProcessingServiceImpl(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }


    @Override
    public boolean transfer(Account from, Account to, double amount) {
        // осуществляет перевод денег amount с счета fromAccountId на toAccountId

        if (!userService.isUserRegistered(from.getUserId()) || !userService.isUserRegistered(to.getUserId())) {
            return false;
        }

        if (from.getBalance() < amount) {
            return false;
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        return true;
    }
}
