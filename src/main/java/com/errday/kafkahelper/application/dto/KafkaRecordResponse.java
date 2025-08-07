package com.errday.kafkahelper.application.dto;

public record KafkaRecordResponse(
        String topicName,
        Integer partition,
        Long offset,
        String key,
        String message) {
}
