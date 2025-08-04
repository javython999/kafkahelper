package com.errday.kafkahelper.application.dto;

public record KafkaBrokerRegisterRequest(
        String alias,
        BootstrapServer bootstrapServer) {

    public String host() { return bootstrapServer.host(); }
    public int port() { return bootstrapServer.port(); }
}
