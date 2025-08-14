package com.errday.kafkahelper.application.port.out.topic;

import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicConfigDescribe;

import java.util.List;

public interface KafkaTopicConfigDescribePort {

    List<KafkaTopicConfigDescribe> configDescribe(KafkaTopic kafkaTopic);
}
