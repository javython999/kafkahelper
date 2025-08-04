package com.errday.kafkahelper.adapter.in.web.dto;

public record TopicPartitionDescribe(
        Integer partition,
        String leader,
        String isr,
        String replicas) {
}
