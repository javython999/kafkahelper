package com.errday.kafkahelper.application.port.in.broker;

import com.errday.kafkahelper.application.dto.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;

public interface KafkaBrokerUpdateUseCase {

    KafkaBrokerResponse update(KafkaBrokerUpdateRequest request);
}
