package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicConfigDescribe;
import com.errday.kafkahelper.domain.KafkaTopicDescribe;

import java.util.List;

public interface KafkaTopicCommandPort {

    KafkaTopic save(KafkaTopic kafkaTopic);
    KafkaTopicDescribe describe(KafkaTopic kafkaTopic);
    List<KafkaTopic> findAll(KafkaBootStrapServer kafkaBootStrapServer);
    List<KafkaTopicConfigDescribe> configDescribe(KafkaTopic kafkaTopic);
    KafkaTopic update(KafkaTopic kafkaTopic);
    KafkaTopic delete(KafkaTopic kafkaTopic);
}
