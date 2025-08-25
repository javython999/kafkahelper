package com.errday.kafkahelper.application.port.in.kafka.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicResponse;

public interface KafkaTopicDeleteUseCase {

    KafkaTopicResponse delete(KafkaTopicRequest request);
}
