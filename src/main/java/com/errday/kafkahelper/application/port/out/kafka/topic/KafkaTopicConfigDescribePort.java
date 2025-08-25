package com.errday.kafkahelper.application.port.out.kafka.topic;

import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import com.errday.kafkahelper.domain.kafka.KafkaTopicConfigDescribe;

import java.util.List;

public interface KafkaTopicConfigDescribePort {

    List<KafkaTopicConfigDescribe> configDescribe(KafkaTopic kafkaTopic);
}
