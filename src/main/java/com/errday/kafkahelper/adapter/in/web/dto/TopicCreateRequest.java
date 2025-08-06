package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;

import java.util.Optional;

public record TopicCreateRequest(
        KafkaBootstrapServerRequest kafkaBootstrapServerRequest,
        String topicName,
        Optional<Integer> partitions,
        Optional<Short> replicationFactor,
        TopicCreateRequestConfig config) {

    public String bootStrapServerAddress() {
        return kafkaBootstrapServerRequest.address();
    }
}
