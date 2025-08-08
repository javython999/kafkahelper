package com.errday.kafkahelper.application.dto;

import java.util.List;

public record KafkaTopicDescribeResponse(
        String topicName,
        boolean internal,
        List<KafkaTopicPartitionDescribeResponse> partitions) {

    public static KafkaTopicDescribeResponse of(String topicName, boolean internal, List<KafkaTopicPartitionDescribeResponse> partitions) {
        return new KafkaTopicDescribeResponse(topicName, internal, partitions);
    }

    public boolean isInternal() {
        return internal;
    }

    public int partitionCount() {
        return partitions.size();
    }
}
