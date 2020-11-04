package ru.lakidemon.jemail.mail.session.impl;

import org.springframework.stereotype.Component;
import ru.lakidemon.jemail.domain.ConnectionInfo;
import ru.lakidemon.jemail.mail.PropertiesWrapper;
import ru.lakidemon.jemail.mail.session.SessionFactory;

import javax.mail.Session;

@Component
public class SessionFactoryImpl implements SessionFactory {
    @Override
    public Session createSession(ConnectionInfo connectionInfo) {
        var builder = PropertiesWrapper.mailWrapper().subSection(connectionInfo.protocolName());
        builder.put("host", connectionInfo.getHost()).put("port", connectionInfo.getPort());

        var session = Session.getInstance(builder.getProperties());
        return session;
    }
}
