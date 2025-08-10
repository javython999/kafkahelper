package com.errday.kafkahelper.application.port.out.broker;

import com.errday.kafkahelper.domain.KafkaBroker;

import java.util.Optional;

public interface KafkaBrokerFindPort {

    Optional<KafkaBroker> findById(long id);
}
