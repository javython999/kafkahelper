package com.errday.kafkahelper.application.port.out.kafka.broker;

import com.errday.kafkahelper.domain.kafka.KafkaBroker;

import java.util.Optional;

public interface KafkaBrokerFindPort {

    Optional<KafkaBroker> findById(long id);
}
