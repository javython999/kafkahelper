package com.errday.kafkahelper.application.port.in.kafka.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;

import java.util.List;

public interface KafkaBrokerListUseCase {

    List<KafkaBrokerResponse> findAll();
}
