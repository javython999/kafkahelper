package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.adapter.in.web.dto.*;
import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.port.in.KafkaTopicPort;
import com.errday.kafkahelper.application.port.out.TopicClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OldKafkaTopicService implements KafkaTopicPort {

    private final TopicClientPort topicClientPort;

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
