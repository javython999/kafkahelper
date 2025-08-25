package com.errday.kafkahelper.application.port.in.kafka.broker;

public interface KafkaBrokerDeleteUseCase {

    boolean deleteById(long id);
}
