package com.errday.kafkahelper.adapter.in.web.dto;

public record TopicConfigDescribeRequest(
        String host,
        Integer port,
        String topicName) {

    public String bootstrapServerAddress() {
        return host + ":" + port;
    }
}
