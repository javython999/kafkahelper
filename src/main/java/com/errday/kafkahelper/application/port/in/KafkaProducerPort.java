package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.domain.model.RegisterRecordRequest;
import org.springframework.scheduling.annotation.Async;

public interface KafkaProducerPort {

    @Async
    void registerRecord(RegisterRecordRequest request);
}
