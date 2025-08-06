package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.domain.KafkaBroker;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KafkaBrokerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;
    private String host;
    private Integer port;

    private LocalDateTime createdAt;

    private KafkaBrokerEntity(String alias, String host, Integer port) {
        this.alias = Objects.requireNonNull(alias);
        this.host = Objects.requireNonNull(host);
        this.port = Objects.requireNonNull(port);
        this.createdAt = LocalDateTime.now();
    }

    public static KafkaBrokerEntity from(KafkaBroker kafkaBroker) {
        return new KafkaBrokerEntity(kafkaBroker.getAlias(), kafkaBroker.getHost(), kafkaBroker.getPort());
    }

    public KafkaBroker toDomain() {
        return KafkaBroker.of(id, alias, host, port);
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
