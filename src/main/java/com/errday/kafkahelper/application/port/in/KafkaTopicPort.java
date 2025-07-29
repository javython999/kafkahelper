package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.domain.model.*;

import java.util.List;
import java.util.Set;

public interface KafkaTopicPort {

    ApiResponse<String> createTopic(TopicCreateRequest request);

    TopicDescribe describeTopic(String topicName);

    Set<String> topicList(BootstrapServer bootstrapServer);

    List<TopicConfigDescribe> describeTopicConfig(String topicName);

    String updateTopicConfig(String topicName, TopicAlterRequest request);

    String deleteTopic(String topicName);
}

