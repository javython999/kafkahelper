package com.errday.kafkahelper.application.service.kafka.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaTopicConfigDescribeResponse;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicRequest;
import com.errday.kafkahelper.application.port.in.kafka.topic.KafkaTopicConfigDescribeUseCase;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicConfigDescribePort;
import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaTopicConfigDescribeService implements KafkaTopicConfigDescribeUseCase {

    private final KafkaTopicConfigDescribePort kafkaTopicConfigDescribePort;

    @Override
    public List<KafkaTopicConfigDescribeResponse> configDescribe(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );

        return kafkaTopicConfigDescribePort.configDescribe(kafkaTopic)
                .stream()
                .map(KafkaTopicConfigDescribeResponse::from)
                .toList();
    }
}
