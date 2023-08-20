package com.learning.processing.inter;

import com.learning.processing.ob.Account;
import com.learning.processing.ob.User;

import java.util.Optional;

public interface AccountService {
    int createAccount(User user, int balance);
    boolean hasAccess(User user, int accountId);
    Optional<Account> getAccountById(User user, int accountId);
    void updateAccount(User user, Account account);
    void closeAccount(User user, int accountId);
}

