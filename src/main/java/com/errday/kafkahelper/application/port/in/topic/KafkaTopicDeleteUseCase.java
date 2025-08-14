package com.errday.kafkahelper.application.port.in.topic;

import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;

public interface KafkaTopicDeleteUseCase {

    KafkaTopicResponse delete(KafkaTopicRequest request);
}
