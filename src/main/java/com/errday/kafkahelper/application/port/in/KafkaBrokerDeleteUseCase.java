package com.errday.kafkahelper.application.port.in;

public interface KafkaBrokerDeleteUseCase {

    boolean deleteById(long id);
}
