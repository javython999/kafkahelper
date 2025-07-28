package com.errday.kafkahelper.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KafkaBroker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;
    private String host;
    private Integer port;

    private LocalDateTime createdAt;

    private KafkaBroker(String alias, String host, Integer port) {
        this.alias = Objects.requireNonNull(alias);
        this.host = Objects.requireNonNull(host);
        this.port = Objects.requireNonNull(port);
        this.createdAt = LocalDateTime.now();
    }

    public static KafkaBroker of(KafkaBrokerRegisterRequest request) {
        return new KafkaBroker(request.alias(), request.host(), request.port());
    }

    public void updateAlias(String alias) {
        this.alias = alias;
    }

    public void updateHost(String host) {
        this.host = host;
    }

    public void updatePort(Integer port) {
        this.port = port;
    }

}
