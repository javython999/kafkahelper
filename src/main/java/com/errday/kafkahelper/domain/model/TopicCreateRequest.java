package com.errday.kafkahelper.domain.model;

import java.util.Optional;

public record TopicCreateRequest(
        BootstrapServer bootstrapServer,
        String topicName,
        Optional<Integer> partitions,
        Optional<Short> replicationFactor,
        TopicCreateRequestConfig config) {

    public String bootStrapServerAddress() {
        return bootstrapServer.address();
    }
}
