package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.domain.model.TopicAlterRequest;
import com.errday.kafkahelper.domain.model.TopicConfigDescribe;
import com.errday.kafkahelper.domain.model.TopicCreateRequest;
import com.errday.kafkahelper.domain.model.TopicDescribe;

import java.util.List;
import java.util.Set;

public interface KafkaTopicPort {

    String createTopic(TopicCreateRequest request);

    TopicDescribe describeTopic(String topicName);

    Set<String> topicList();

    List<TopicConfigDescribe> describeTopicConfig(String topicName);

    String updateTopicConfig(String topicName, TopicAlterRequest request);

    String deleteTopic(String topicName);
}

