package com.errday.kafkahelper.application.dto.kafka;

import com.errday.kafkahelper.domain.kafka.KafkaTopicConfig;

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
