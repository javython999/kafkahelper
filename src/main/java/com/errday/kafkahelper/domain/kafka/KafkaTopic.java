package com.errday.kafkahelper.domain.kafka;

import lombok.Getter;

@Getter
public class KafkaTopic {

    private KafkaBootStrapServer bootStrapServer;
    private String topicName;
    private Integer partitions;
    private Short replicationFactor;
    private KafkaTopicConfig topicConfig;

    private KafkaTopic(KafkaBootStrapServer bootStrapServer, String topicName, Integer partitions, Short replicationFactor, KafkaTopicConfig topicConfig) {
        this.bootStrapServer = bootStrapServer;
        this.topicName = topicName;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
        this.topicConfig = topicConfig;
    }

    public static KafkaTopic of(KafkaBootStrapServer bootStrapServer, String topicName, Integer partitions, Short replicationFactor, KafkaTopicConfig topicConfig) {
        return new KafkaTopic(bootStrapServer, topicName, partitions, replicationFactor, topicConfig);
    }

    public static KafkaTopic of(KafkaBootStrapServer bootStrapServer, String topicName) {
        return new KafkaTopic(bootStrapServer, topicName, null, null, null);
    }
}
