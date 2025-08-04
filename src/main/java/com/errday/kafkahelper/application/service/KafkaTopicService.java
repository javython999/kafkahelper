package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.adapter.in.web.dto.*;
import com.errday.kafkahelper.application.dto.BootstrapServer;
import com.errday.kafkahelper.application.port.in.KafkaTopicPort;
import com.errday.kafkahelper.application.port.out.TopicClientPort;
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

    public ApiResponse<List<TopicConfigDescribe>> describeTopicConfig(TopicConfigDescribeRequest request) {
        return topicClientPort.describeTopicConfig(request);
    }

    public ApiResponse<String> updateTopicConfig(String topicName, TopicEditRequest request) {
        return topicClientPort.updateTopicConfig(topicName, request);
    }

    public ApiResponse<String> deleteTopic(TopicDeleteRequest request) {
        return topicClientPort.deleteTopic(request);
    }
}
