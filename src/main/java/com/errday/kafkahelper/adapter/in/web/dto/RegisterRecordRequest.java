package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.BootstrapServer;

public record RegisterRecordRequest(
        BootstrapServer bootstrapServer,
        String topicName,
        String key,
        String message) {

    public String bootstrapServerAddress() {
        return bootstrapServer.address();
    }
}
