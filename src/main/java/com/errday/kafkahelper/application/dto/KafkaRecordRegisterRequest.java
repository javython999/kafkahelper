package com.errday.kafkahelper.application.dto;

public record KafkaRecordRegisterRequest(
        KafkaBootstrapServerRequest bootstrapServer,
        String topicName,
        String key,
        String message) {
}
