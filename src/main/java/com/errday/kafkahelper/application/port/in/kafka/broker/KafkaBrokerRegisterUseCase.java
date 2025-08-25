package com.errday.kafkahelper.application.port.in.kafka.broker;


import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;

public interface KafkaBrokerRegisterUseCase {

    KafkaBrokerResponse register(KafkaBrokerRegisterRequest request);
}
