package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;

public record RegisterRecordRequest(
        KafkaBootstrapServerRequest kafkaBootstrapServerRequest,
        String topicName,
        String key,
        String message) {

    public String bootstrapServerAddress() {
        return kafkaBootstrapServerRequest.address();
    }
}
