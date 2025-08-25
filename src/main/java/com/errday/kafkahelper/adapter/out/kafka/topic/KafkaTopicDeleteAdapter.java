package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.KafkaAdminClient;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicDeletePort;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicDeleteAdapter implements KafkaTopicDeletePort {

    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public KafkaTopic delete(KafkaTopic kafkaTopic) {
        try (AdminClient admin = kafkaAdminClient.kafkaAdminClient(kafkaTopic.getBootStrapServer())) {

            admin.deleteTopics(List.of(kafkaTopic.getTopicName()))
                    .all()
                    .get();

            return kafkaTopic;
        } catch (ExecutionException | InterruptedException e) {

            log.error("An error occurred while deletion of topic {}", kafkaTopic.getTopicName(), e);

            throw new RuntimeException(e);
        }
    }
}
