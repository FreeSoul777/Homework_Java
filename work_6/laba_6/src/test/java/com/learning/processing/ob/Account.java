package com.learning.processing.ob;

public class Account {
    private int id;
    private final int userId;
    private double balance;

    public Account(User user, int balance, int accountId) {
        this.userId = user.getId();
        this.balance = balance;
        this.id = accountId;
        user.getAccounts().put(accountId, this);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

}
