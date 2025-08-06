package com.errday.kafkahelper.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class KafkaBroker {

    private Long id;
    private String alias;
    private String host;
    private Integer port;
    private LocalDateTime createdAt;

    private KafkaBroker(Long id, String alias, String host, Integer port) {
        this.id = id;
        this.alias = Objects.requireNonNull(alias);
        this.host = Objects.requireNonNull(host);
        this.port = Objects.requireNonNull(port);
        this.createdAt = LocalDateTime.now();
    }

    public static KafkaBroker of(Long id, String alias, String host, Integer port) {
        return new KafkaBroker(id, alias, host, port);
    }

    public void update(String alias, String host, Integer port) {
        this.alias = Objects.requireNonNull(alias);
        this.host = Objects.requireNonNull(host);
        this.port = Objects.requireNonNull(port);
    }

}
