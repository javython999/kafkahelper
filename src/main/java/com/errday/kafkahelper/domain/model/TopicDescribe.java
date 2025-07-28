package com.errday.kafkahelper.domain.model;

import java.util.List;

public record TopicDescribe(
        String topicName,
        boolean internal,
        List<TopicPartitionDescribe> partitions) {

    public boolean isInternal() {
        return internal;
    }

    public int partitionCount() {
        return partitions.size();
    }
}
