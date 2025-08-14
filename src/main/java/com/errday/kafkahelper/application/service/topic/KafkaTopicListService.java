package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.in.topic.KafkaTopicListUseCase;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicListPort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
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
