package com.errday.kafkahelper.application.dto;

public record KafkaTopicPartitionDescribeResponse(
        Integer partition,
        String leader,
        String isr,
        String replicas) {
}
