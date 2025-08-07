package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.application.dto.KafkaRecordRegisterRequest;

public interface KafkaRecordRegisterUseCase {

    boolean register(KafkaRecordRegisterRequest request);
}
