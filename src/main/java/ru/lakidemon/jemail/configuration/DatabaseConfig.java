package ru.lakidemon.jemail.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import ru.lakidemon.jemail.domain.Account;

@Configuration
@EntityScan(basePackageClasses = Account.class)
public class DatabaseConfig {
}
