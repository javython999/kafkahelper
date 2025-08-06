package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.KafkaAdminClient;
import com.errday.kafkahelper.adapter.out.kafka.util.KafkaFieldUtils;
import com.errday.kafkahelper.application.port.out.KafkaTopicCommandPort;
import com.errday.kafkahelper.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicAdapter implements KafkaTopicCommandPort {

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

    @Override
    public List<KafkaTopicConfigDescribe> configDescribe(KafkaTopic kafkaTopic) {

        try (AdminClient admin = kafkaAdminClient.kafkaAdminClient(kafkaTopic.getBootStrapServer())) {

            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, kafkaTopic.getTopicName());

            DescribeConfigsResult result = admin.describeConfigs(Collections.singleton(resource));

            Config topicConfig = result.all().get().get(resource);

            return topicConfig.entries()
                    .stream()
                    .map(configEntry -> KafkaTopicConfigDescribe.of(
                            configEntry.name(),
                            KafkaFieldUtils.dotCaseToCamelCase(configEntry.name()),
                            configEntry.value(),
                            configEntry.isDefault(),
                            configEntry.isReadOnly(),
                            configEntry.isSensitive()
                    ))
                    .toList();

        } catch (ExecutionException | InterruptedException e) {

            log.error("Failed to retrieve configuration for = {}", kafkaTopic.getTopicName(), e);

            throw new RuntimeException(e);
        }

    }

    @Override
    public KafkaTopic update(KafkaTopic kafkaTopic) {

        try (AdminClient admin = kafkaAdminClient.kafkaAdminClient(kafkaTopic.getBootStrapServer())) {
            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, kafkaTopic.getTopicName());

            List<AlterConfigOp> configs = KafkaFieldUtils.getNonNullFields(kafkaTopic.getTopicConfig())
                    .entrySet()
                    .stream()
                    .map(entry -> new AlterConfigOp(
                            new ConfigEntry(entry.getKey(), String.valueOf(entry.getValue())),
                            AlterConfigOp.OpType.SET
                    ))
                    .toList();


            Map<ConfigResource, Collection<AlterConfigOp>> configChanges = Map.of(resource, configs);
            admin.incrementalAlterConfigs(configChanges)
                    .all()
                    .get();

            return kafkaTopic;
        } catch (ExecutionException | InterruptedException e) {

            log.error("An error occurred while updating the configuration for topic {}", kafkaTopic.getTopicName(), e);

            throw new RuntimeException(e);
        }

    }

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
