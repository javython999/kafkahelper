package com.errday.kafkahelper.domain.kafka;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaRecordTest {

    private final KafkaBootStrapServer kafkaBootStrapServer = KafkaBootstrapServerFixture.mock();

    @Test
    void of() {

        String topicName = "mockTopic";
        Integer partitions = 1;
        Long offset = 1L;
        String key = "key";
        String message = "message";

        KafkaRecord kafkaRecord = KafkaRecord.of(
                kafkaBootStrapServer,
                topicName,
                partitions,
                offset,
                key,
                message
        );

        assertThat(kafkaRecord).isNotNull();
        assertThat(kafkaRecord.bootstrapServerAddress()).isEqualTo(kafkaBootStrapServer.address());
        assertThat(kafkaRecord.getTopicName()).isEqualTo(topicName);
        assertThat(kafkaRecord.getPartitions()).isEqualTo(partitions);
        assertThat(kafkaRecord.getOffset()).isEqualTo(offset);
        assertThat(kafkaRecord.getKey()).isEqualTo(key);
        assertThat(kafkaRecord.getMessage()).isEqualTo(message);
    }

    @Test
    void of2() {
        String topicName = "mockTopic";
        String key = "key";
        String message = "message";

        KafkaRecord kafkaRecord = KafkaRecord.of(
                kafkaBootStrapServer,
                topicName,
                key,
                message
        );

        assertThat(kafkaRecord).isNotNull();
        assertThat(kafkaRecord.bootstrapServerAddress()).isEqualTo(kafkaBootStrapServer.address());
        assertThat(kafkaRecord.getTopicName()).isEqualTo(topicName);
        assertThat(kafkaRecord.getKey()).isEqualTo(key);
        assertThat(kafkaRecord.getMessage()).isEqualTo(message);
    }

    @Test
    void bootstrapServerAddress() {
        String topicName = "mockTopic";
        Integer partitions = 1;
        Long offset = 1L;
        String key = "key";
        String message = "message";

        KafkaRecord kafkaRecord = KafkaRecord.of(
                kafkaBootStrapServer,
                topicName,
                partitions,
                offset,
                key,
                message
        );

        assertThat(kafkaRecord.bootstrapServerAddress()).isEqualTo(kafkaBootStrapServer.address());
    }
}