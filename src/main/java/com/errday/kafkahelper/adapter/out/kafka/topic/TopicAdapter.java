package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.util.KafkaUtils;
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
    public ApiResponse<String> createTopic(TopicCreateRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, request.bootStrapServerAddress());

        try (AdminClient admin = AdminClient.create(config)) {

            NewTopic topic = new NewTopic(request.topicName(), request.partitions(), request.replicationFactor())
                    .configs(KafkaUtils.getNonNullFields(request.config()));
            admin.createTopics(List.of(topic)).all().get();

            return ApiResponse.success(
                    String.format("Successfully created topic: %s",
                    request.topicName()), request.topicName()
            );
        } catch (ExecutionException | InterruptedException e) {

            return ApiResponse.error(
                    String.format("An error occurred while creating the topic: %s", e.getCause()),
                    request.topicName()
            );
        }
    }

    @Override
    public ApiResponse<TopicDescribe> describeTopic(TopicDescribeRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, request.bootStrapServerAddress());

        try (AdminClient admin = AdminClient.create(config)) {

            DescribeTopicsResult result = admin.describeTopics(List.of(request.topicName()));

            TopicDescription description = result.topicNameValues()
                    .get(request.topicName())
                    .get();

            List<TopicPartitionDescribe> partitions = description.partitions()
                    .stream()
                    .map(partition -> new TopicPartitionDescribe(
                            partition.partition(),
                            partition.leader().toString(),
                            partition.isr().toString(),
                            partition.replicas().toString()))
                    .toList();

            return ApiResponse.success("success", new TopicDescribe(request.topicName(), description.isInternal(), partitions));
        } catch (ExecutionException | InterruptedException e) {

            log.error("describe topic = {} fail", request.topicName(), e);

            return ApiResponse.error("error", null);
        }

    }

    @Override
    public ApiResponse<List<String>> topicList(BootstrapServer bootstrapServer) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer.address());

        try (AdminClient admin = AdminClient.create(config)) {

            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(false);

            ListTopicsResult topics = admin.listTopics(options);

            List<String> sorted = topics.names().get().stream()
                    .sorted()
                    .toList();

            return ApiResponse.success("success", sorted);

        } catch (ExecutionException | InterruptedException e) {

            log.error("topic list fail", e);

            return ApiResponse.error("error", Collections.emptyList());
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
                            KafkaUtils.dotCaseToCamelCase(configEntry.name()),
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
    public ApiResponse<String> updateTopicConfig(String topicName, TopicEditRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, request.bootStrapServerAddress());

        try (AdminClient admin = AdminClient.create(config)) {
            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topicName);

            List<AlterConfigOp> configs = KafkaUtils.getNonNullFields(request.config())
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

            return ApiResponse.success("edit topic success", "success");
        } catch (ExecutionException | InterruptedException e) {

            log.error("create topic = {} fail",topicName, e);

            return ApiResponse.success("An error occurred while edit", "success");
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
