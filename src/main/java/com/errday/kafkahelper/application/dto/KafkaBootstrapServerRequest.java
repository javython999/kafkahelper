package com.errday.kafkahelper.application.dto;

public record KafkaBootstrapServerRequest(String host, Integer port) {

    public String address() {
        return host + ":" + port;
    }
}
