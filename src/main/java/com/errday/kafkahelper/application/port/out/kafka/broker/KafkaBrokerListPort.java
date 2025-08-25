package com.errday.kafkahelper.application.port.out.kafka.broker;

import com.errday.kafkahelper.domain.kafka.KafkaBroker;

import java.util.List;

public interface KafkaBrokerListPort {

    List<KafkaBroker> findAll();
}
