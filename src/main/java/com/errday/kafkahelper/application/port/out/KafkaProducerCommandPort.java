package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.KafkaRecord;

public interface KafkaProducerCommandPort {

    boolean save(KafkaRecord kafkaRecord);
}
