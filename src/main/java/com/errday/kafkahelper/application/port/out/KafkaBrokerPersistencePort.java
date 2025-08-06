package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.KafkaBroker;

public interface KafkaBrokerPersistencePort {

    KafkaBroker save(KafkaBroker kafkaBroker);

    KafkaBroker update(KafkaBroker kafkaBroker);

    boolean delete(long id);
}
