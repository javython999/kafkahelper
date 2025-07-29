package com.errday.kafkahelper.domain.model;

public record KafkaBrokerRegisterRequest(
        String alias,
        BootstrapServer bootstrapServer) {

    public String host() { return bootstrapServer.host(); }
    public int port() { return bootstrapServer.port(); }
}
