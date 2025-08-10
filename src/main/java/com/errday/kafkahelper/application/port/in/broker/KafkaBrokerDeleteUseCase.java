package com.errday.kafkahelper.application.port.in.broker;

public interface KafkaBrokerDeleteUseCase {

    boolean deleteById(long id);
}
