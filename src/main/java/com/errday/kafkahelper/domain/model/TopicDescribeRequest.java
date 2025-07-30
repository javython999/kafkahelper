package com.errday.kafkahelper.domain.model;

public record TopicDescribeRequest(
        BootstrapServer bootstrapServer,
        String topicName) {

    public String bootStrapServerAddress() {
        return bootstrapServer.address();
    }
}
