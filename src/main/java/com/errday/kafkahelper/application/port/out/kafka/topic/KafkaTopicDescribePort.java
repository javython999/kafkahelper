package com.errday.kafkahelper.application.port.out.kafka.topic;

import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import com.errday.kafkahelper.domain.kafka.KafkaTopicDescribe;

public interface KafkaTopicDescribePort {

    KafkaTopicDescribe describe(KafkaTopic kafkaTopic);
}
