package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.model.TopicAlterRequest;
import com.errday.kafkahelper.domain.model.TopicConfigDescribe;
import com.errday.kafkahelper.domain.model.TopicCreateRequest;
import com.errday.kafkahelper.domain.model.TopicDescribe;

import java.util.List;
import java.util.Set;

public interface TopicClientPort {

    String createTopic(TopicCreateRequest request);

    TopicDescribe describeTopic(String topicName);

    Set<String> topicList();

    List<TopicConfigDescribe> describeTopicConfig(String topicName);

    String updateTopicConfig(String topicName, TopicAlterRequest request);

    String deleteTopic(String topicName);
}
