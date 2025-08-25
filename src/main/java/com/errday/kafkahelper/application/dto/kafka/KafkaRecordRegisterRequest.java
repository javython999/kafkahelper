package com.errday.kafkahelper.application.dto.kafka;

public record KafkaRecordRegisterRequest(
        KafkaBootstrapServerRequest bootstrapServer,
        String topicName,
        String key,
        String message) {
}
