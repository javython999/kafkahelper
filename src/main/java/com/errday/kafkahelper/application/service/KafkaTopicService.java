package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.port.in.KafkaTopicPort;
import com.errday.kafkahelper.application.port.out.TopicClientPort;
import com.errday.kafkahelper.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaTopicService implements KafkaTopicPort {

    private final TopicClientPort topicClientPort;

    public ApiResponse<String> createTopic(TopicCreateRequest request) {
        return topicClientPort.createTopic(request);
    }

    public ApiResponse<TopicDescribe> describeTopic(TopicDescribeRequest request) {
        return topicClientPort.describeTopic(request);
    }

    public ApiResponse<List<String>> topicList(BootstrapServer bootstrapServer) {
        return topicClientPort.topicList(bootstrapServer);
    }

    public List<TopicConfigDescribe> describeTopicConfig(String topicName) {
        return topicClientPort.describeTopicConfig(topicName);
    }

    public ApiResponse<String> updateTopicConfig(String topicName, TopicEditRequest request) {
        return topicClientPort.updateTopicConfig(topicName, request);
    }

    public String deleteTopic(String topicName) {
        return topicClientPort.deleteTopic(topicName);
    }
}
