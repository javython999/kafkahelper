package com.errday.kafkahelper.application.dto.kafka;

public record KafkaTopicPartitionDescribeResponse(
        Integer partition,
        String leader,
        String isr,
        String replicas) {
}
