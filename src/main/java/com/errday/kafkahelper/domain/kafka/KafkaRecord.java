package com.errday.kafkahelper.domain.kafka;

import lombok.Getter;

@Getter
public class KafkaRecord {

    private KafkaBootStrapServer bootStrapServer;
    private String topicName;
    private Integer partitions;
    private Long offset;
    private String key;
    private String message;

    private KafkaRecord(KafkaBootStrapServer bootStrapServer, String topicName, Integer partitions, Long offset, String key, String message) {
        this.bootStrapServer = bootStrapServer;
        this.topicName = topicName;
        this.partitions = partitions;
        this.offset = offset;
        this.key = key;
        this.message = message;
    }

    public static KafkaRecord of(KafkaBootStrapServer bootStrapServer, String topicName, Integer partitions, Long offset, String key, String message) {
        return new KafkaRecord(bootStrapServer, topicName, partitions, offset, key, message);
    }

    public static KafkaRecord of(KafkaBootStrapServer bootStrapServer, String topicName, String key, String message) {
        return of(bootStrapServer, topicName, null, null, key, message);
    }

    public String bootstrapServerAddress() {
        return bootStrapServer.address();
    }
}
