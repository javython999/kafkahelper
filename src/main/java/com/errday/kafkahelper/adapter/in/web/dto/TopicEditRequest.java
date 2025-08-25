package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;

public record TopicEditRequest(
        KafkaBootstrapServerRequest kafkaBootstrapServerRequest,
        TopicEditConfig config
        ) {

}
