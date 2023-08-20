package com.learning.processing.inter;

import com.learning.processing.ob.Account;
import com.learning.processing.ob.User;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private int lastAccountId = 0;

    @Override
    public int createAccount(User user, int balance) {
        // создает счет для пользователя, если он существует
        Account account = new Account(user, balance, ++lastAccountId);
        user.getAccounts().put(account.getId(), account);
        return lastAccountId;
    }

    @Override
    public void closeAccount(User user, int accountId) {
        user.getAccounts().remove(accountId);
    }

    @Override
    public boolean hasAccess(User user, int accountId) {
        // проверяет, что неавторизованный пользователь или другой юзер не может получить доступ к чужому счёту
        Optional<Account> optionalAccount = Optional.ofNullable(user.getAccounts().get(accountId));
        if (optionalAccount.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<Account> getAccountById(User user, int accountId) {
        // возращает счет по Id
        return Optional.ofNullable(user.getAccounts().get(accountId));
    }

    @Override
    public void updateAccount(User user, Account account) {
        // обновляет данные счета
        user.getAccounts().put(account.getId(), account);
    }
}

