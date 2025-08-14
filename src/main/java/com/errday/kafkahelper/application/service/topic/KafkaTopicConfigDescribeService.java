package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaTopicConfigDescribeResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.port.in.topic.KafkaTopicConfigDescribeUseCase;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicConfigDescribePort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
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
