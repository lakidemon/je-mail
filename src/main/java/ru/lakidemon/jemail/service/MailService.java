package ru.lakidemon.jemail.service;

import ru.lakidemon.jemail.domain.Account;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.List;

public interface MailService {

    List<Message> getMessages(Account account) throws MessagingException;

}
