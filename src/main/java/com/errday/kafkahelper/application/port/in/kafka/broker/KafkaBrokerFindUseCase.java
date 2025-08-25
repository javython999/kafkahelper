package com.errday.kafkahelper.application.port.in.kafka.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;

public interface KafkaBrokerFindUseCase {

    KafkaBrokerResponse findById(long id);
}
