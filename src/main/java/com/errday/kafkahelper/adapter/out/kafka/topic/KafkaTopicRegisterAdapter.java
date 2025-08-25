package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.KafkaAdminClient;
import com.errday.kafkahelper.adapter.out.kafka.util.KafkaFieldUtils;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicRegisterPort;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicRegisterAdapter implements KafkaTopicRegisterPort {

    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public KafkaTopic save(KafkaTopic kafkaTopic) {
        try (AdminClient admin = kafkaAdminClient.kafkaAdminClient(kafkaTopic.getBootStrapServer())) {

            NewTopic topic = new NewTopic(kafkaTopic.getTopicName(), Optional.ofNullable(kafkaTopic.getPartitions()), Optional.ofNullable(kafkaTopic.getReplicationFactor()))
                    .configs(KafkaFieldUtils.getNonNullFields(kafkaTopic.getTopicConfig()));
            admin.createTopics(List.of(topic)).all().get();

            return kafkaTopic;

        } catch (ExecutionException | InterruptedException e) {
            log.error("Save kafka topic error", e);
            throw new RuntimeException(e);
        }
    }
}
