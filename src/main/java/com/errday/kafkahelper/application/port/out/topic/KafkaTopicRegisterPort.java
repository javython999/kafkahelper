package com.errday.kafkahelper.application.port.out.topic;

import com.errday.kafkahelper.domain.KafkaTopic;

public interface KafkaTopicRegisterPort {

    KafkaTopic save(KafkaTopic kafkaTopic);
}
