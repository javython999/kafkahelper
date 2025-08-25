package com.errday.kafkahelper.application.port.in.kafka.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaTopicConfigDescribeResponse;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicRequest;

import java.util.List;

public interface KafkaTopicConfigDescribeUseCase {

    List<KafkaTopicConfigDescribeResponse> configDescribe(KafkaTopicRequest request);
}
