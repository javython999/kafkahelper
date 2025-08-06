package com.errday.kafkahelper.application.dto;

import com.errday.kafkahelper.domain.KafkaTopicConfig;

public record KafkaTopicRequest(
        KafkaBootstrapServerRequest bootstrapServer,
        String topicName,
        Integer partitions,
        Short replicationFactor,
        KafkaTopicConfig config) {

    public String bootstrapServerAddress() {
        return bootstrapServer.address();
    }
}
