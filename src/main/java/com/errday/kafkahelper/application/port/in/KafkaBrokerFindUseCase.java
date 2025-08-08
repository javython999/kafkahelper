package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;

public interface KafkaBrokerFindUseCase {

    KafkaBrokerResponse findById(long id);
}
