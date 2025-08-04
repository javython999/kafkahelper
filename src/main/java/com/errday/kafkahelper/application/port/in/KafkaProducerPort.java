package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.adapter.in.web.dto.RegisterRecordRequest;
import org.springframework.scheduling.annotation.Async;

public interface KafkaProducerPort {

    @Async
    void registerRecord(RegisterRecordRequest request);
}
