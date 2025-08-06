package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.KafkaBroker;

import java.util.List;

public interface KafkaBrokerQueryPort {

    List<KafkaBroker> findAll();
    KafkaBroker findById(long id);
}
