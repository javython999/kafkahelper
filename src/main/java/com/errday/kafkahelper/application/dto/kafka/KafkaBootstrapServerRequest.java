package com.errday.kafkahelper.application.dto.kafka;

public record KafkaBootstrapServerRequest(String host, Integer port) {

    public String address() {
        return host + ":" + port;
    }
}
