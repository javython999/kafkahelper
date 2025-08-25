package com.errday.kafkahelper.adapter.out.kafka.producer;

import com.errday.kafkahelper.adapter.out.kafka.KafkaTemplateManager;
import com.errday.kafkahelper.application.port.out.kafka.producer.KafkaRecordRegisterPort;
import com.errday.kafkahelper.domain.kafka.KafkaRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaRecordRegisterAdapter implements KafkaRecordRegisterPort {

    private final KafkaTemplateManager kafkaTemplateManager;

    @Override
    public boolean save(KafkaRecord kafkaRecord) {
        KafkaTemplate<String, String> kafkaTemplate = kafkaTemplateManager.getKafkaTemplate(kafkaRecord.bootstrapServerAddress());

        try {
            kafkaTemplate.send(kafkaRecord.getTopicName(), kafkaRecord.getKey(), kafkaRecord.getMessage());
            return true;
        } catch (Exception e) {
            log.error("registerRecord fail: {}", e.getMessage(), e);
            return false;
        }
    }
}
