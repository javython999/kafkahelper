package com.errday.kafkahelper.application.port.out.topic;

import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicDescribe;

public interface KafkaTopicDescribePort {

    KafkaTopicDescribe describe(KafkaTopic kafkaTopic);
}
