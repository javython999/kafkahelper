package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.dto.*;
import com.errday.kafkahelper.application.port.in.*;
import com.errday.kafkahelper.application.port.out.KafkaTopicCommandPort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaTopicService implements KafkaTopicRegisterUseCase, KafkaTopicDescribeUseCase, KafkaTopicListUseCase, KafkaTopicConfigDescribeUseCase, KafkaTopicUpdateUseCase, KafkaTopicDeleteUseCase {

    private final KafkaTopicCommandPort kafkaTopicCommandPort;

    @Override
    public KafkaTopicResponse register(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );
        KafkaTopic saved = kafkaTopicCommandPort.save(kafkaTopic);
        return new KafkaTopicResponse(saved.getTopicName());
    }

    @Override
    public KafkaTopicDescribeResponse describe(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );
        return KafkaTopicDescribeResponse.from(kafkaTopicCommandPort.describe(kafkaTopic));
    }

    @Override
    public List<KafkaTopicResponse> findAll(KafkaBootstrapServerRequest request) {
        return kafkaTopicCommandPort.findAll(KafkaBootStrapServer.of(request.host(), request.port()))
                .stream()
                .map(kafkaTopic -> new KafkaTopicResponse(kafkaTopic.getTopicName()))
                .toList();
    }

    @Override
    public List<KafkaTopicConfigDescribeResponse> configDescribe(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );

        return kafkaTopicCommandPort.configDescribe(kafkaTopic)
                .stream()
                .map(KafkaTopicConfigDescribeResponse::from)
                .toList();
    }

    @Override
    public KafkaTopicResponse update(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );


        return new KafkaTopicResponse(kafkaTopicCommandPort.update(kafkaTopic).getTopicName());
    }

    @Override
    public KafkaTopicResponse delete(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );

        return new KafkaTopicResponse(kafkaTopicCommandPort.delete(kafkaTopic).getTopicName());
    }
}
