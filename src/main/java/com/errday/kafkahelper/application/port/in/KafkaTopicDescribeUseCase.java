package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.application.dto.KafkaTopicDescribeResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;

public interface KafkaTopicDescribeUseCase {

    KafkaTopicDescribeResponse describe(KafkaTopicRequest request);
}
