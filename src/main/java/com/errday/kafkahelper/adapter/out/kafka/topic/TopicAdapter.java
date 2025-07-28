package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.util.KafkaCommandUtils;
import com.errday.kafkahelper.application.port.out.TopicClientPort;
import com.errday.kafkahelper.domain.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TopicAdapter implements TopicClientPort {

    @Value("${kafka.bootstrap-server}")
    private String BOOTSTRAP_SERVER;

    @Override
    public String createTopic(TopicCreateRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        try (AdminClient admin = AdminClient.create(config)) {

            NewTopic topic = new NewTopic(request.topicName(), request.partitions(), request.replicationFactor())
                    .configs(KafkaCommandUtils.getNonNullFields(request.config()));
            admin.createTopics(List.of(topic)).all().get();

            return "create topic = " + request.topicName() + " success";
        } catch (ExecutionException e) {

            return "topic '" + request.topicName() + "' already exists";

        } catch (InterruptedException e) {

            log.error("create topic = {} fail",request.topicName(), e);

            return "create topic = " + request.topicName() + " fail";
        }
    }

    @Override
    public TopicDescribe describeTopic(String topicName) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        try (AdminClient admin = AdminClient.create(config)) {

            DescribeTopicsResult result = admin.describeTopics(List.of(topicName));

            TopicDescription description = result.topicNameValues()
                    .get(topicName)
                    .get();

            List<TopicPartitionDescribe> partitions = description.partitions()
                    .stream()
                    .map(partition -> new TopicPartitionDescribe(
                            partition.partition(),
                            partition.leader().toString(),
                            partition.isr().toString(),
                            partition.replicas().toString()))
                    .toList();

            return new TopicDescribe(topicName, description.isInternal(), partitions);
        } catch (ExecutionException | InterruptedException e) {

            log.error("describe topic = {} fail",topicName, e);

            return null;
        }

    }

    @Override
    public Set<String> topicList() {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        try (AdminClient admin = AdminClient.create(config)) {

            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(false);

            ListTopicsResult topics = admin.listTopics(options);

            return topics.names().get();

        } catch (ExecutionException | InterruptedException e) {

            log.error("topic list fail", e);

            return Collections.emptySet();
        }
    }

    @Override
    public List<TopicConfigDescribe> describeTopicConfig(String topicName) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        try (AdminClient admin = AdminClient.create(config)) {

            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topicName);

            DescribeConfigsResult result = admin.describeConfigs(Collections.singleton(resource));

            Config topicConfig = result.all().get().get(resource);

            return topicConfig.entries()
                    .stream()
                    .map(configEntry -> new TopicConfigDescribe(
                            configEntry.name(),
                            configEntry.value(),
                            configEntry.isDefault(),
                            configEntry.isReadOnly(),
                            configEntry.isSensitive()
                    ))
                    .toList();

        } catch (ExecutionException | InterruptedException e) {

            log.error("describe topic config = {} fail",topicName, e);

            return Collections.emptyList();
        }
    }

    @Override
    public String updateTopicConfig(String topicName, TopicAlterRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        try (AdminClient admin = AdminClient.create(config)) {
            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topicName);

            // 변경할 설정 정의
            List<AlterConfigOp> configs = KafkaCommandUtils.getNonNullFields(request)
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

            return "update topic config = " + topicName + " success";
        } catch (ExecutionException | InterruptedException e) {

            log.error("create topic = {} fail",topicName, e);

            return "update topic config = " + topicName + " fail";
        }

    }

    @Override
    public String deleteTopic(String topicName) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

        try (AdminClient admin = AdminClient.create(config)) {

            admin.deleteTopics(List.of(topicName))
                    .all()
                    .get();

            return "delete topic = " + topicName + " success";
        } catch (ExecutionException | InterruptedException e) {

            log.error("delete topic = {} fail",topicName, e);

            return "delete topic = " + topicName + " fail";
        }

    }
}
