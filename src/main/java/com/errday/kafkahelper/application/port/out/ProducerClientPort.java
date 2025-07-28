package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.model.RegisterRecordRequest;

public interface ProducerClientPort {

    void registerRecord(RegisterRecordRequest request);
}
