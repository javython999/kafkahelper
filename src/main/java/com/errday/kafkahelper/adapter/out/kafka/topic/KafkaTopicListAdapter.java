package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.KafkaAdminClient;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicListPort;
import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicListAdapter implements KafkaTopicListPort {

    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public List<KafkaTopic> findAll(KafkaBootStrapServer kafkaBootStrapServer) {
        try (AdminClient admin = kafkaAdminClient.kafkaAdminClient(kafkaBootStrapServer)) {

            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(false);

            ListTopicsResult topics = admin.listTopics(options);

            return topics.names().get()
                    .stream()
                    .sorted()
                    .map(topicName -> KafkaTopic.of(kafkaBootStrapServer, topicName))
                    .toList();

        } catch (ExecutionException | InterruptedException e) {
            log.error("Failed to retrieve topic list from bootstrap server = {}.", kafkaBootStrapServer.address(), e);
            throw new RuntimeException(e);
        }
    }
}
