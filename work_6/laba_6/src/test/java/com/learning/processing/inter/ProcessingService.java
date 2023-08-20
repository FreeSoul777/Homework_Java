package com.learning.processing.inter;

import com.learning.processing.ob.Account;
import com.learning.processing.ob.InMemoryBase;

public interface ProcessingService {
    boolean transfer(Account from, Account to, double amount);
}