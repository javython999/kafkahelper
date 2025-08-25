package com.errday.kafkahelper.application.port.in.kafka.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;

public interface KafkaBrokerUpdateUseCase {

    KafkaBrokerResponse update(KafkaBrokerUpdateRequest request);
}
