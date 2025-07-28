package com.errday.kafkahelper.adapter.out.kafka.producer;

import com.errday.kafkahelper.application.port.out.ProducerClientPort;
import com.errday.kafkahelper.domain.model.RegisterRecordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerAdapter implements ProducerClientPort {

    @Value("${kafka.bootstrap-server}")
    private String BOOTSTRAP_SERVER;

    @Override
    public void  registerRecord(RegisterRecordRequest request) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);

        try {
            kafkaTemplate.send(request.topicName(), request.key(), request.message());
        } catch (Exception e) {
            log.error("registerRecord fail: {}", e.getMessage(), e);
        }
    }


}
