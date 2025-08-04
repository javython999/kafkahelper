package com.errday.kafkahelper.adapter.in.web.dto;

public record KafkaSseRequest(
        String topicName,
        String host,
        Integer port) {

    public String bootstrapServerAddress() {
        return host + ":" + port;
    }
}
