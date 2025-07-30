package com.errday.kafkahelper.domain.model;

public record TopicEditRequest(
        BootstrapServer bootstrapServer,
        TopicEditConfig config
        ) {

    public String bootStrapServerAddress() {
        return bootstrapServer.address();
    }
}
