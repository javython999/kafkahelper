package com.errday.kafkahelper.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
        this.alias = alias;
        this.host = host;
        this.port = port;
        this.createdAt = LocalDateTime.now();
    }

    public static KafkaBroker of(KafkaBrokerRegisterRequest request) {
        return new KafkaBroker(request.alias(), request.host(), request.port());
    }

}
