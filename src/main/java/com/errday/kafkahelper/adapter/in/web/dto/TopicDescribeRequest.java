package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.BootstrapServer;

public record TopicDescribeRequest(
        BootstrapServer bootstrapServer,
        String topicName) {

    public String bootStrapServerAddress() {
        return bootstrapServer.address();
    }
}
