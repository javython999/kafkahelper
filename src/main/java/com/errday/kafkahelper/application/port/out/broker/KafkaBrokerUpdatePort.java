package com.errday.kafkahelper.application.port.out.broker;

import com.errday.kafkahelper.domain.KafkaBroker;

public interface KafkaBrokerUpdatePort {

    KafkaBroker update(KafkaBroker kafkaBroker);
}
