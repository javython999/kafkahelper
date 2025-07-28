package com.errday.kafkahelper.domain.model;

public record TopicPartitionDescribe(
        Integer partition,
        String leader,
        String isr,
        String replicas) {
}
