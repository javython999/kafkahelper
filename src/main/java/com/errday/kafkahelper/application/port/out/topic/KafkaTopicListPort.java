package com.errday.kafkahelper.application.port.out.topic;

import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;

import java.util.List;

public interface KafkaTopicListPort {

    List<KafkaTopic> findAll(KafkaBootStrapServer kafkaBootStrapServer);
}
