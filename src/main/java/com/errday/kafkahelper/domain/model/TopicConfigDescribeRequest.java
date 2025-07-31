package com.errday.kafkahelper.domain.model;

public record TopicConfigDescribeRequest(
        String host,
        Integer port,
        String topicName) {

    public String bootstrapServerAddress() {
        return host + ":" + port;
    }
}
