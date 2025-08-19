package com.errday.kafkahelper.application.port.out.producer;

import com.errday.kafkahelper.domain.KafkaRecord;

public interface KafkaRecordRegisterPort {

    boolean save(KafkaRecord kafkaRecord);
}
