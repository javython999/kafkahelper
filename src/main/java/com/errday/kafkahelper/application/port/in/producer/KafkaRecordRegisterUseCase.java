package com.errday.kafkahelper.application.port.in.producer;

import com.errday.kafkahelper.application.dto.KafkaRecordRegisterRequest;

public interface KafkaRecordRegisterUseCase {

    boolean register(KafkaRecordRegisterRequest request);
}
