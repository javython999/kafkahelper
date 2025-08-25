package com.errday.kafkahelper.application.port.out.kafka.topic;

import com.errday.kafkahelper.domain.kafka.KafkaTopic;

public interface KafkaTopicRegisterPort {

    KafkaTopic save(KafkaTopic kafkaTopic);
}
