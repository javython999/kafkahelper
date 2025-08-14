package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.in.topic.KafkaTopicRegisterUseCase;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicRegisterPort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaTopicRegisterService implements KafkaTopicRegisterUseCase {

    private final KafkaTopicRegisterPort kafkaTopicRegisterPort;

    @Override
    public KafkaTopicResponse register(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );
        KafkaTopic saved = kafkaTopicRegisterPort.save(kafkaTopic);
        return new KafkaTopicResponse(saved.getTopicName());
    }
}
