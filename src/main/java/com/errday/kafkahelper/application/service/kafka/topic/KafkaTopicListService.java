package com.errday.kafkahelper.application.service.kafka.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.in.kafka.topic.KafkaTopicListUseCase;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicListPort;
import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaTopicListService implements KafkaTopicListUseCase {

    private final KafkaTopicListPort kafkaTopicListPort;

    @Override
    public List<KafkaTopicResponse> findAll(KafkaBootstrapServerRequest request) {
        return kafkaTopicListPort.findAll(KafkaBootStrapServer.of(request.host(), request.port()))
                .stream()
                .map(kafkaTopic -> new KafkaTopicResponse(kafkaTopic.getTopicName()))
                .toList();
    }
}
