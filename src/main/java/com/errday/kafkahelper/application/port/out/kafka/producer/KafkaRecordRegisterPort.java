package com.errday.kafkahelper.application.port.out.kafka.producer;

import com.errday.kafkahelper.domain.kafka.KafkaRecord;

public interface KafkaRecordRegisterPort {

    boolean save(KafkaRecord kafkaRecord);
}
