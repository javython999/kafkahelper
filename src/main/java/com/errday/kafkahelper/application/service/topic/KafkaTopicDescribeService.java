package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaTopicDescribeResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicPartitionDescribeResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.port.in.topic.KafkaTopicDescribeUseCase;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicDescribePort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicDescribe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaTopicDescribeService implements KafkaTopicDescribeUseCase {

    private final KafkaTopicDescribePort kafkaTopicDescribePort;

    @Override
    public KafkaTopicDescribeResponse describe(KafkaTopicRequest request) {
        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.partitions(),
                request.replicationFactor(),
                request.config()
        );

        KafkaTopicDescribe describe = kafkaTopicDescribePort.describe(kafkaTopic);

        List<KafkaTopicPartitionDescribeResponse> partitions = describe.getPartitions()
                .stream()
                .map(topicPartitionDescribe ->
                        new KafkaTopicPartitionDescribeResponse(
                                topicPartitionDescribe.getPartition(),
                                topicPartitionDescribe.getLeader(),
                                topicPartitionDescribe.getIsr(),
                                topicPartitionDescribe.getReplicas()
                        ))
                .toList();


        return KafkaTopicDescribeResponse.of(describe.getTopicName(), describe.isInternal(), partitions);
    }
}
