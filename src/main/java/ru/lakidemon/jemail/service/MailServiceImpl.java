package ru.lakidemon.jemail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lakidemon.jemail.domain.Account;
import ru.lakidemon.jemail.mail.session.StoreFactory;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final StoreFactory storeFactory;

    @Override
    public List<Message> getMessages(Account account) throws MessagingException {
        var store = storeFactory.createStore(account.getIncomingServer(), account.getCredentials());
        var folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        return Arrays.asList(folder.search(new ReceivedDateTerm(ComparisonTerm.GE, new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(30)))));
    }
}
