package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.port.in.KafkaTopicPort;
import com.errday.kafkahelper.application.port.out.TopicClientPort;
import com.errday.kafkahelper.domain.model.TopicAlterRequest;
import com.errday.kafkahelper.domain.model.TopicConfigDescribe;
import com.errday.kafkahelper.domain.model.TopicCreateRequest;
import com.errday.kafkahelper.domain.model.TopicDescribe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class KafkaTopicService implements KafkaTopicPort {

    private final TopicClientPort topicClientPort;

    public String createTopic(TopicCreateRequest request) {
        return topicClientPort.createTopic(request);
    }

    public TopicDescribe describeTopic(String topicName) {
        return topicClientPort.describeTopic(topicName);
    }

    public Set<String> topicList() {
        return topicClientPort.topicList();
    }

    public List<TopicConfigDescribe> describeTopicConfig(String topicName) {
        return topicClientPort.describeTopicConfig(topicName);
    }

    public String updateTopicConfig(String topicName, TopicAlterRequest request) {
        return topicClientPort.updateTopicConfig(topicName, request);
    }

    public String deleteTopic(String topicName) {
        return topicClientPort.deleteTopic(topicName);
    }
}
