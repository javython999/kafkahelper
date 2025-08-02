package com.errday.kafkahelper.domain.model;

public record RegisterRecordRequest(
        BootstrapServer bootstrapServer,
        String topicName,
        String key,
        String message) {

    public String bootstrapServerAddress() {
        return bootstrapServer.address();
    }
}
