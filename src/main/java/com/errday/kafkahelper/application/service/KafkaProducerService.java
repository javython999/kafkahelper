package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.port.in.KafkaProducerPort;
import com.errday.kafkahelper.application.port.out.ProducerClientPort;
import com.errday.kafkahelper.adapter.in.web.dto.RegisterRecordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService implements KafkaProducerPort {

    private final ProducerClientPort producerClientPort;

    @Async
    public void registerRecord(RegisterRecordRequest request) {
        producerClientPort.registerRecord(request);
    }
}
