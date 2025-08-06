package com.errday.kafkahelper.application.dto;

public record KafkaBrokerRegisterRequest(
        String alias,
        KafkaBootstrapServerRequest kafkaBootstrapServerRequest) {

    public String host() { return kafkaBootstrapServerRequest.host(); }
    public int port() { return kafkaBootstrapServerRequest.port(); }
}
