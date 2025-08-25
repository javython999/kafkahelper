package com.errday.kafkahelper.domain.kafka;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaTopicDescribeTest {

    @Test
    void of() {
        String topicName = "mockTopic";
        boolean internal = true;
        List<KafkaTopicPartitionDescribe> partitions = List.of(
                KafkaTopicPartitionDescribe.of(null, null, null, null),
                KafkaTopicPartitionDescribe.of(null, null, null, null)
        );

        KafkaTopicDescribe kafkaTopicDescribe = KafkaTopicDescribe.of(topicName, internal, partitions);

        assertThat(kafkaTopicDescribe).isNotNull();
        assertThat(kafkaTopicDescribe.getTopicName()).isEqualTo(topicName);
        assertThat(kafkaTopicDescribe.isInternal()).isEqualTo(internal);
        assertThat(kafkaTopicDescribe.getPartitions()).isEqualTo(partitions);

    }

    @Test
    void isInternal() {
        String topicName = "mockTopic";
        boolean internal = false;
        List<KafkaTopicPartitionDescribe> partitions = List.of(
                KafkaTopicPartitionDescribe.of(null, null, null, null),
                KafkaTopicPartitionDescribe.of(null, null, null, null)
        );

        KafkaTopicDescribe kafkaTopicDescribe = KafkaTopicDescribe.of(topicName, internal, partitions);

        assertThat(kafkaTopicDescribe.isInternal()).isEqualTo(internal);

    }

    @Test
    void partitionCount() {
        String topicName = "mockTopic";
        boolean internal = false;
        List<KafkaTopicPartitionDescribe> partitions = List.of(
                KafkaTopicPartitionDescribe.of(null, null, null, null),
                KafkaTopicPartitionDescribe.of(null, null, null, null)
        );

        KafkaTopicDescribe kafkaTopicDescribe = KafkaTopicDescribe.of(topicName, internal, partitions);

        assertThat(kafkaTopicDescribe.partitionCount()).isEqualTo(partitions.size());

    }
}