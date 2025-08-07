package com.errday.kafkahelper.adapter.out.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KafkaTemplateManager {

    private final Map<String, KafkaTemplate<String, String>> templateCache = new ConcurrentHashMap<>();

    public KafkaTemplate<String, String> getKafkaTemplate(String bootstrapServerAddress) {
        return templateCache.computeIfAbsent(bootstrapServerAddress, this::createKafkaTemplate);
    }

    private KafkaTemplate<String, String> createKafkaTemplate(String bootstrapServer) {

        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(configs);
        return new KafkaTemplate<>(factory);
    }

}
