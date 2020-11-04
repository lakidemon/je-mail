package ru.lakidemon.jemail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakidemon.jemail.domain.Account;
import ru.lakidemon.jemail.repository.AccountsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;

    @Override
    public List<Account> getAccounts() {
        return accountsRepository.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        accountsRepository.save(account);
    }

    @Override
    public void removeAccount(Account account) {
        accountsRepository.delete(account);
    }
}
