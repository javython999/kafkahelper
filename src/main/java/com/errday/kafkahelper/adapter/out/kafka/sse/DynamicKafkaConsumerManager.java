package com.errday.kafkahelper.adapter.out.kafka.sse;

import com.errday.kafkahelper.adapter.in.web.dto.KafkaSseRequest;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicKafkaConsumerManager {

    private final SseEmitters sseEmitters;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Map<String, KafkaConsumer<String, String>> consumers = new ConcurrentHashMap<>();

    public void startListening(KafkaSseRequest request) {
        if (consumers.containsKey(request.topicName())) return;

        Properties props = new Properties();
        props.put("bootstrap.servers", request.bootstrapServerAddress());
        props.put("group.id", "dynamic-consumer-" + UUID.randomUUID());
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "latest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(request.topicName()));
        consumers.put(request.topicName(), consumer);


        sseEmitters.registerCleanupCallback(request.topicName(), () -> {
            if (!sseEmitters.hasEmitters(request.topicName())) {
                stopListening(request.topicName());
            }
        });

        executor.submit(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        sseEmitters.sendToTopic(request.topicName(), record);
                    }
                }
            } catch (WakeupException e) {
                log.info("Kafka Consumer for topic [{}] is shutting down", request.topicName());
            } catch (Exception e) {
                log.error("Error in Kafka Consumer for topic [{}]: {}", request.topicName(), e.getMessage(), e);
            } finally {
                consumer.close();
                log.info("Kafka Consumer for topic [{}] closed", request.topicName());
            }
        });
    }

    public void stopListening(String topic) {
        KafkaConsumer<String, String> consumer = consumers.remove(topic);
        if (consumer != null) {
            log.info("Kafka Consumer for topic [{}] closed due to inactivity", topic);
            consumer.wakeup(); // poll 중인 스레드 종료 유도
        }
    }

    @PreDestroy
    public void destroy() {
        executor.shutdown();
    }
}