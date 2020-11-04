package ru.lakidemon.jemail.mail.session;

import ru.lakidemon.jemail.domain.ConnectionInfo;

import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

public interface TransportFactory {

    Transport createTransport(ConnectionInfo connectionInfo, PasswordAuthentication credentials);
}
