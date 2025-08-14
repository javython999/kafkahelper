package com.errday.kafkahelper.application.port.in.topic;

import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;

public interface KafkaTopicUpdateUseCase {

    KafkaTopicResponse update(KafkaTopicRequest request);
}
