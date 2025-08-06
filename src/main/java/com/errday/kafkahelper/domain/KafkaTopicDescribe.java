package com.errday.kafkahelper.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class KafkaTopicDescribe {

    private String topicName;
    private boolean internal;
    private List<KafkaTopicPartitionDescribe> partitions;

    private KafkaTopicDescribe(String topicName, boolean internal, List<KafkaTopicPartitionDescribe> partitions) {
        this.topicName = topicName;
        this.internal = internal;
        this.partitions = partitions;
    }

    public static KafkaTopicDescribe of(String topicName, boolean internal, List<KafkaTopicPartitionDescribe> partitions) {
        return new KafkaTopicDescribe(topicName, internal, partitions);
    }

    public boolean isInternal() {
        return internal;
    }

    public int partitionCount() {
        return partitions.size();
    }
}
