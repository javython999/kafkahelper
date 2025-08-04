package com.errday.kafkahelper.application.port.in;

import com.errday.kafkahelper.adapter.in.web.dto.*;
import com.errday.kafkahelper.application.dto.BootstrapServer;

import java.util.List;

public interface KafkaTopicPort {

    ApiResponse<String> createTopic(TopicCreateRequest request);

    ApiResponse<TopicDescribe> describeTopic(TopicDescribeRequest request);

    ApiResponse<List<String>> topicList(BootstrapServer bootstrapServer);

    ApiResponse<List<TopicConfigDescribe>> describeTopicConfig(TopicConfigDescribeRequest request);

    ApiResponse<String> updateTopicConfig(String topicName, TopicEditRequest request);

    ApiResponse<String> deleteTopic(TopicDeleteRequest request);
}

