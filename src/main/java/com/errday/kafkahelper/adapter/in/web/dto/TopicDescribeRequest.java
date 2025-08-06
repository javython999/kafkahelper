package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;

public record TopicDescribeRequest(
        KafkaBootstrapServerRequest kafkaBootstrapServerRequest,
        String topicName) {

    public String bootStrapServerAddress() {
        return kafkaBootstrapServerRequest.address();
    }
}
