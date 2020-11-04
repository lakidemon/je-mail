package ru.lakidemon.jemail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "servers")
public class ConnectionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String host;
    private int port;
    private Protocol protocol;
    private boolean useSsl;

    public String protocolName() {
        return useSsl ? protocol.getSecureProtocolName() : protocol.getProtocolName();
    }
}
