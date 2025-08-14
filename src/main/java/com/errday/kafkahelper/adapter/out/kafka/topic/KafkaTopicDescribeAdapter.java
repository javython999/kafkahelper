package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.KafkaAdminClient;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicDescribePort;
import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicDescribe;
import com.errday.kafkahelper.domain.KafkaTopicPartitionDescribe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicDescribeAdapter implements KafkaTopicDescribePort {

    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public KafkaTopicDescribe describe(KafkaTopic kafkaTopic) {

        try (AdminClient admin = kafkaAdminClient.kafkaAdminClient(kafkaTopic.getBootStrapServer())) {

            DescribeTopicsResult result = admin.describeTopics(List.of(kafkaTopic.getTopicName()));

            TopicDescription description = result.topicNameValues()
                    .get(kafkaTopic.getTopicName())
                    .get();

            List<KafkaTopicPartitionDescribe> partitions = description.partitions()
                    .stream()
                    .map(partition -> KafkaTopicPartitionDescribe.of(
                            partition.partition(),
                            partition.leader().toString(),
                            partition.isr().toString(),
                            partition.replicas().toString()))
                    .toList();

            return KafkaTopicDescribe.of(kafkaTopic.getTopicName(), description.isInternal(), partitions);
        } catch (ExecutionException | InterruptedException e) {
            log.error("Describe topic = {} fail", kafkaTopic.getTopicName(), e);
            throw new RuntimeException(e);
        }
    }
}
