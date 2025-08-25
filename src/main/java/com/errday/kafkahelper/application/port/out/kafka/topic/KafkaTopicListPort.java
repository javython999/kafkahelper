package com.errday.kafkahelper.application.port.out.kafka.topic;

import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;

import java.util.List;

public interface KafkaTopicListPort {

    List<KafkaTopic> findAll(KafkaBootStrapServer kafkaBootStrapServer);
}
