package ru.lakidemon.jemail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lakidemon.jemail.domain.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {
}
