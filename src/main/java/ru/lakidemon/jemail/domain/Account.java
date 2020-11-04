package ru.lakidemon.jemail.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String displayName;
    private String fromName;
    private String signature;
    @OneToOne(cascade = CascadeType.ALL)
    private ConnectionInfo incomingServer;
    @OneToOne(cascade = CascadeType.ALL)
    private ConnectionInfo outgoingServer;
    @Embedded
    private Credentials credentials;
    private boolean removeReceivedMessages;
}
