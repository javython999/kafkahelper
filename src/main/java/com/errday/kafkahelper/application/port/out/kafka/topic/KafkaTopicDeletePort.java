package com.errday.kafkahelper.application.port.out.kafka.topic;

import com.errday.kafkahelper.domain.kafka.KafkaTopic;

public interface KafkaTopicDeletePort {

    KafkaTopic delete(KafkaTopic kafkaTopic);
}
