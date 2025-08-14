package com.errday.kafkahelper.application.port.out.topic;

import com.errday.kafkahelper.domain.KafkaTopic;

public interface KafkaTopicDeletePort {

    KafkaTopic delete(KafkaTopic kafkaTopic);
}
