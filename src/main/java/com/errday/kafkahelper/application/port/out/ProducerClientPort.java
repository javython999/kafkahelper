package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.adapter.in.web.dto.RegisterRecordRequest;

public interface ProducerClientPort {

    void registerRecord(RegisterRecordRequest request);
}
