package com.errday.kafkahelper.domain.model;

public record RegisterRecordRequest(
        String topicName,
        String key,
        String message) {
}
