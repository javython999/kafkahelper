package com.errday.kafkahelper.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class KafkaTopicPartitionDescribeTest {

    @Test
    void of() {

        Integer partition = 1;
        String leader = "leader";
        String isr = "isr";
        String replicas = "replicas";

        KafkaTopicPartitionDescribe kafkaTopicPartitionDescribe = KafkaTopicPartitionDescribe.of(partition, leader, isr, replicas);

        assertThat(kafkaTopicPartitionDescribe).isNotNull();
        assertThat(kafkaTopicPartitionDescribe.getLeader()).isEqualTo(leader);
        assertThat(kafkaTopicPartitionDescribe.getIsr()).isEqualTo(isr);
        assertThat(kafkaTopicPartitionDescribe.getReplicas()).isEqualTo(replicas);

    }

}