package com.errday.kafkahelper.application.service.kafka.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.in.kafka.topic.KafkaTopicUpdateUseCase;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicUpdatePort;
import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaTopicUpdateService implements KafkaTopicUpdateUseCase {

    private final KafkaTopicUpdatePort kafkaTopicUpdatePort;

    @Override
    public KafkaTopicResponse update(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );

        return new KafkaTopicResponse(kafkaTopicUpdatePort.update(kafkaTopic).getTopicName());
    }
}
