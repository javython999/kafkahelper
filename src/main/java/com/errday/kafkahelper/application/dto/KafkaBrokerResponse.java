package com.errday.kafkahelper.application.dto;

import com.errday.kafkahelper.domain.KafkaBroker;

public record KafkaBrokerResponse(
        Long id,
        String alias,
        KafkaBootstrapServerRequest bootstrapServer) {

    public static KafkaBrokerResponse of(Long id, String alias, String host, int port) {
        return new KafkaBrokerResponse(id, alias, new KafkaBootstrapServerRequest(host, port));
    }

    public static KafkaBrokerResponse from(KafkaBroker kafkaBroker) {
        return of(kafkaBroker.getId(), kafkaBroker.getAlias(), kafkaBroker.getHost(), kafkaBroker.getPort());
    }

    public String host() { return bootstrapServer.host(); }

    public int port() { return bootstrapServer.port(); }
}
