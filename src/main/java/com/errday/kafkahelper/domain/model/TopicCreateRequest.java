package com.errday.kafkahelper.domain.model;

import java.util.Optional;

public record TopicCreateRequest(
        String topicName,
        Optional<Integer> partitions,
        Optional<Short> replicationFactor,
        TopicCreateRequestConfig config) {
}
