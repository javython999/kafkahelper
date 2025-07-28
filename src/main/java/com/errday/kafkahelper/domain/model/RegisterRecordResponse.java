package com.errday.kafkahelper.domain.model;

public record RegisterRecordResponse(
        String topicName,
        Integer partition,
        Long offset,
        String key,
        String message) {

    public static RegisterRecordResponse empty() {
        return new RegisterRecordResponse(null, null, null, null, null);
    }
}
