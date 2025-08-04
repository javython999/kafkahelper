package com.errday.kafkahelper.adapter.in.web.dto;

import com.errday.kafkahelper.application.dto.BootstrapServer;

public record TopicEditRequest(
        BootstrapServer bootstrapServer,
        TopicEditConfig config
        ) {

    public String bootStrapServerAddress() {
        return bootstrapServer.address();
    }
}
