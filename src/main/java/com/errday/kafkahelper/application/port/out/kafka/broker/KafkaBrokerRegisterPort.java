package com.errday.kafkahelper.application.port.out.kafka.broker;

import com.errday.kafkahelper.domain.kafka.KafkaBroker;

public interface KafkaBrokerRegisterPort {

    KafkaBroker save(KafkaBroker kafkaBroker);
}
