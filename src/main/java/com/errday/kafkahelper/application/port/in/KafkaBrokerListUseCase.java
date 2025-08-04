package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;

import java.util.List;

public interface KafkaBrokerListUseCase {

    List<KafkaBrokerResponse> findAll();
    KafkaBrokerResponse findById(long id);
}
