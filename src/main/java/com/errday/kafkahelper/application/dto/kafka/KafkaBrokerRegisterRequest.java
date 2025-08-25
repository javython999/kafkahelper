package com.errday.kafkahelper.application.dto.kafka;

public record KafkaBrokerRegisterRequest(
        String alias,
        KafkaBootstrapServerRequest bootstrapServer) {

    public String host() { return bootstrapServer.host(); }
    public int port() { return bootstrapServer.port(); }
}
