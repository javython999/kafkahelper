package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.in.topic.KafkaTopicUpdateUseCase;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicUpdatePort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
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
