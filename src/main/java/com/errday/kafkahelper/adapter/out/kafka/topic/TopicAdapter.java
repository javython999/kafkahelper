package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.in.web.dto.*;
import com.errday.kafkahelper.adapter.out.kafka.util.KafkaFieldUtils;
import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.port.out.TopicClientPort;
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

    @Override
    public ApiResponse<List<TopicConfigDescribe>> describeTopicConfig(TopicConfigDescribeRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, request.bootstrapServerAddress());

        try (AdminClient admin = AdminClient.create(config)) {

            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, request.topicName());

            DescribeConfigsResult result = admin.describeConfigs(Collections.singleton(resource));

            Config topicConfig = result.all().get().get(resource);

            List<TopicConfigDescribe> topicConfigDescribes = topicConfig.entries()
                    .stream()
                    .map(configEntry -> new TopicConfigDescribe(
                            configEntry.name(),
                            KafkaFieldUtils.dotCaseToCamelCase(configEntry.name()),
                            configEntry.value(),
                            configEntry.isDefault(),
                            configEntry.isReadOnly(),
                            configEntry.isSensitive()
                    ))
                    .toList();

            return ApiResponse.success("success", topicConfigDescribes);

        } catch (ExecutionException | InterruptedException e) {

            log.error("describe topic config = {} fail", request.topicName(), e);

            return ApiResponse.error("fail", Collections.emptyList());
        }
    }

    @Override
    public ApiResponse<String> updateTopicConfig(String topicName, TopicEditRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, request.bootStrapServerAddress());

        try (AdminClient admin = AdminClient.create(config)) {
            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topicName);

            List<AlterConfigOp> configs = KafkaFieldUtils.getNonNullFields(request.config())
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

            return ApiResponse.success("An error occurred while edit", "error");
        }

    }

    @Override
    public ApiResponse<String> deleteTopic(TopicDeleteRequest request) {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, request.bootStrapServerAddress());

        try (AdminClient admin = AdminClient.create(config)) {

            admin.deleteTopics(List.of(request.topicName()))
                    .all()
                    .get();

            return ApiResponse.success("delete topic success", request.topicName());
        } catch (ExecutionException | InterruptedException e) {

            log.error("delete topic = {} fail", request.topicName(), e);

            return ApiResponse.error("An error occurred while delete topic", request.topicName());
        }

    }
}
