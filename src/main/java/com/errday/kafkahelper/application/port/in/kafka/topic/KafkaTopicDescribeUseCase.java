package com.errday.kafkahelper.application.port.in.kafka.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaTopicDescribeResponse;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicRequest;

public interface KafkaTopicDescribeUseCase {

    KafkaTopicDescribeResponse describe(KafkaTopicRequest request);
}
