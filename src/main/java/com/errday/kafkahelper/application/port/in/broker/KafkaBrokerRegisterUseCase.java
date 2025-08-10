package com.errday.kafkahelper.application.port.in.broker;


import com.errday.kafkahelper.application.dto.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;

public interface KafkaBrokerRegisterUseCase {

    KafkaBrokerResponse register(KafkaBrokerRegisterRequest request);
}
