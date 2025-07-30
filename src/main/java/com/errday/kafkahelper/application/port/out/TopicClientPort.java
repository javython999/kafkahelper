package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.domain.model.*;

import java.util.List;

public interface TopicClientPort {

    ApiResponse<String> createTopic(TopicCreateRequest request);

    ApiResponse<TopicDescribe> describeTopic(TopicDescribeRequest request);

    ApiResponse<List<String>> topicList(BootstrapServer bootstrapServer);

    List<TopicConfigDescribe> describeTopicConfig(String topicName);

    ApiResponse<String> updateTopicConfig(String topicName, TopicEditRequest request);

    String deleteTopic(String topicName);
}
