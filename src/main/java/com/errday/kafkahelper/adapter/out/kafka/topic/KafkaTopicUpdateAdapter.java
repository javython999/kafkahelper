package com.errday.kafkahelper.adapter.out.kafka.topic;

import com.errday.kafkahelper.adapter.out.kafka.KafkaAdminClient;
import com.errday.kafkahelper.adapter.out.kafka.util.KafkaFieldUtils;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicUpdatePort;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AlterConfigOp;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaTopicUpdateAdapter implements KafkaTopicUpdatePort {

    private final KafkaAdminClient kafkaAdminClient;

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
}
