package com.errday.kafkahelper.application.port.out;

import com.errday.kafkahelper.adapter.in.web.dto.*;
import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;

import java.util.List;

public interface TopicClientPort {

    ApiResponse<List<TopicConfigDescribe>> describeTopicConfig(TopicConfigDescribeRequest request);

    ApiResponse<String> updateTopicConfig(String topicName, TopicEditRequest request);

    ApiResponse<String> deleteTopic(TopicDeleteRequest request);
}
