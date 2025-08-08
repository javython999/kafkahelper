package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.KafkaBroker;

public interface KafkaBrokerFindPort {

    KafkaBroker findById(long id);
}
