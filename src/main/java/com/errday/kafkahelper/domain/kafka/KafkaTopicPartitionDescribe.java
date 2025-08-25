package com.errday.kafkahelper.domain.kafka;

import lombok.Getter;

@Getter
public class KafkaTopicPartitionDescribe {
    private Integer partition;
    private String leader;
    private String isr;
    private String replicas;

    private KafkaTopicPartitionDescribe(Integer partition, String leader, String isr, String replicas) {
        this.partition = partition;
        this.leader = leader;
        this.isr = isr;
        this.replicas = replicas;
    }

    public static KafkaTopicPartitionDescribe of(Integer partition, String leader, String isr, String replicas) {
        return new KafkaTopicPartitionDescribe(partition, leader, isr, replicas);
    }
}
