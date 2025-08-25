package com.errday.kafkahelper.application.port.in.kafka.producer;

import com.errday.kafkahelper.application.dto.kafka.KafkaRecordRegisterRequest;

public interface KafkaRecordRegisterUseCase {

    boolean register(KafkaRecordRegisterRequest request);
}
