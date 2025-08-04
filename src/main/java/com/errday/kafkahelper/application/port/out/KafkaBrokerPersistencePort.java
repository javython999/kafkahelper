package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.model.KafkaBroker;

public interface KafkaBrokerPersistencePort {

    KafkaBroker save(KafkaBroker kafkaBroker);

    KafkaBroker update(KafkaBroker kafkaBroker);

    boolean delete(long id);
}
