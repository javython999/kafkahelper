package com.errday.kafkahelper.application.port.in.topic;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;

import java.util.List;

public interface KafkaTopicListUseCase {

    List<KafkaTopicResponse> findAll(KafkaBootstrapServerRequest request);
}
