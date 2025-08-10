package com.errday.kafkahelper.application.port.out.broker;

import com.errday.kafkahelper.domain.KafkaBroker;

public interface KafkaBrokerRegisterPort {

    KafkaBroker save(KafkaBroker kafkaBroker);
}
