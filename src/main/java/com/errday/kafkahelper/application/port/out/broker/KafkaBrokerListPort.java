package com.errday.kafkahelper.application.port.out.broker;

import com.errday.kafkahelper.domain.KafkaBroker;

import java.util.List;

public interface KafkaBrokerListPort {

    List<KafkaBroker> findAll();
}
