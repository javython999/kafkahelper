package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.BootstrapServer;

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
