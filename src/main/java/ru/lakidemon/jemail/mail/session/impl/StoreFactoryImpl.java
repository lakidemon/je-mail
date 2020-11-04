package ru.lakidemon.jemail.mail.session.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.lakidemon.jemail.domain.ConnectionInfo;
import ru.lakidemon.jemail.domain.Credentials;
import ru.lakidemon.jemail.mail.session.SessionFactory;
import ru.lakidemon.jemail.mail.session.StoreFactory;

import javax.mail.Store;

@Component
@RequiredArgsConstructor
public class StoreFactoryImpl implements StoreFactory {
    private final SessionFactory sessionFactory;

    @Override
    @SneakyThrows
    public Store createStore(ConnectionInfo connectionInfo, Credentials credentials) {
        var session = sessionFactory.createSession(connectionInfo);
        var store = session.getStore(connectionInfo.protocolName());
        store.connect(credentials.getUsername(), credentials.getPassword());
        return store;
    }

}
