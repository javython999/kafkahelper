package com.errday.kafkahelper.domain.model;

public record TopicDeleteRequest(
        BootstrapServer bootstrapServer,
        String topicName) {

    public String bootStrapServerAddress() {
        return bootstrapServer.address();
    }
}
