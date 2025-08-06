package com.errday.kafkahelper.application.dto;

import com.errday.kafkahelper.adapter.in.web.dto.TopicPartitionDescribe;
import com.errday.kafkahelper.domain.KafkaTopicDescribe;

import java.util.List;

public record KafkaTopicDescribeResponse(
        String topicName,
        boolean internal,
        List<TopicPartitionDescribe> partitions) {

    public static KafkaTopicDescribeResponse from(KafkaTopicDescribe  kafkaTopicDescribe) {
        return new KafkaTopicDescribeResponse(
                kafkaTopicDescribe.getTopicName(),
                kafkaTopicDescribe.isInternal(),
                kafkaTopicDescribe.getPartitions()
                        .stream()
                        .map(describe ->
                                new TopicPartitionDescribe(
                                        describe.getPartition(),
                                        describe.getLeader(),
                                        describe.getIsr(),
                                        describe.getReplicas()
                                ))
                        .toList()
        );
    }

    public boolean isInternal() {
        return internal;
    }

    public int partitionCount() {
        return partitions.size();
    }
}
