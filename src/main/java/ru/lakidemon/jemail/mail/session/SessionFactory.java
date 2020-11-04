package ru.lakidemon.jemail.mail.session;

import ru.lakidemon.jemail.domain.ConnectionInfo;

import javax.mail.Session;

public interface SessionFactory {

    Session createSession(ConnectionInfo connectionInfo);
}
