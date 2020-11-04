package ru.lakidemon.jemail.mail.session.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.lakidemon.jemail.domain.ConnectionInfo;
import ru.lakidemon.jemail.mail.session.SessionFactory;
import ru.lakidemon.jemail.mail.session.TransportFactory;

import javax.mail.PasswordAuthentication;
import javax.mail.Transport;


@Component
@RequiredArgsConstructor
public class TransportFactoryImpl implements TransportFactory {
    private final SessionFactory sessionFactory;

    @Override
    @SneakyThrows
    public Transport createTransport(ConnectionInfo connectionInfo, PasswordAuthentication credentials) {
        var session = sessionFactory.createSession(connectionInfo);
        var transport = session.getTransport(connectionInfo.protocolName());
        transport.connect(credentials.getUserName(), credentials.getPassword());
        return transport;
    }
}
