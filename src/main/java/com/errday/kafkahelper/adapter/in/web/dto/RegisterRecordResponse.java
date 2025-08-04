package com.errday.kafkahelper.adapter.in.web.dto;

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
