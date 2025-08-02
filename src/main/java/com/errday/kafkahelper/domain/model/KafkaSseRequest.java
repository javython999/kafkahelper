package com.errday.kafkahelper.domain.model;

public record KafkaSseRequest(
        String topicName,
        String host,
        Integer port) {

    public String bootstrapServerAddress() {
        return host + ":" + port;
    }
}
