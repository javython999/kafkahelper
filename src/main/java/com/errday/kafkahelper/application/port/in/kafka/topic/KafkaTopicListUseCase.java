package com.errday.kafkahelper.application.port.in.kafka.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicResponse;

import java.util.List;

public interface KafkaTopicListUseCase {

    List<KafkaTopicResponse> findAll(KafkaBootstrapServerRequest request);
}
