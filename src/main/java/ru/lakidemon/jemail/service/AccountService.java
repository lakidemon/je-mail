package ru.lakidemon.jemail.service;

import ru.lakidemon.jemail.domain.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts();

    void saveAccount(Account account);

    void removeAccount(Account account);

}
