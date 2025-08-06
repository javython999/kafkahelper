package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.application.dto.KafkaTopicConfigDescribeResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;

import java.util.List;

public interface KafkaTopicConfigDescribeUseCase {

    List<KafkaTopicConfigDescribeResponse> configDescribe(KafkaTopicRequest request);
}
