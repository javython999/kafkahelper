package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.KafkaAdminClient;
import com.errday.kafkahelper.adapter.out.kafka.util.KafkaFieldUtils;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicConfigDescribePort;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import com.errday.kafkahelper.domain.kafka.KafkaTopicConfigDescribe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicConfigDescribeAdapter implements KafkaTopicConfigDescribePort {

    private final KafkaAdminClient kafkaAdminClient;

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
}
