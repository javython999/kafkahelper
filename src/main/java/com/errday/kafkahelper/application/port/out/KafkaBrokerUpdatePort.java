package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.KafkaBroker;

public interface KafkaBrokerUpdatePort {

    KafkaBroker update(KafkaBroker kafkaBroker);
}
