package ru.lakidemon.jemail.mail.session;

import ru.lakidemon.jemail.domain.ConnectionInfo;
import ru.lakidemon.jemail.domain.Credentials;

import javax.mail.Store;

public interface StoreFactory {

    Store createStore(ConnectionInfo connectionInfo, Credentials credentials);

}
