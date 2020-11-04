package ru.lakidemon.jemail.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Protocol {
    IMAP("imap", "imaps"), POP3("pop3", "pop3s"), SMTP("smtp", "smtps");

    private final String protocolName;
    private final String secureProtocolName;

}
