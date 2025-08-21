package com.errday.kafkahelper.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaTopicTest {

    private KafkaBootStrapServer kafkaBootStrapServer = KafkaBootstrapServerFixture.mock();

    @Test
    void of() {
        String topicName = "mockTopic";
        Integer partitions = 1;
        Short replicationFactor = 1;
        KafkaTopicConfig topicConfig = new KafkaTopicConfig();

        KafkaTopic kafkaTopic = KafkaTopic.of(
                kafkaBootStrapServer,
                topicName,
                partitions,
                replicationFactor,
                topicConfig
        );

        assertThat(kafkaTopic).isNotNull();
        assertThat(kafkaTopic.getTopicName()).isEqualTo(topicName);
        assertThat(kafkaTopic.getPartitions()).isEqualTo(partitions);
        assertThat(kafkaTopic.getReplicationFactor()).isEqualTo(replicationFactor);
        assertThat(kafkaTopic.getTopicConfig()).isEqualTo(topicConfig);
    }

    @Test
    void of2() {
        String topicName = "mockTopic";

        KafkaTopic kafkaTopic = KafkaTopic.of(
                kafkaBootStrapServer,
                topicName
        );

        assertThat(kafkaTopic).isNotNull();
        assertThat(kafkaTopic.getTopicName()).isEqualTo(topicName);
        assertThat(kafkaTopic.getPartitions()).isNull();
        assertThat(kafkaTopic.getReplicationFactor()).isNull();
        assertThat(kafkaTopic.getTopicConfig()).isNull();
    }
}