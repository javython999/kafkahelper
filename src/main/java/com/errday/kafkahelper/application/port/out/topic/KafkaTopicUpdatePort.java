package com.errday.kafkahelper.application.port.out.topic;

import com.errday.kafkahelper.domain.KafkaTopic;

public interface KafkaTopicUpdatePort {

    KafkaTopic update(KafkaTopic kafkaTopic);
}
