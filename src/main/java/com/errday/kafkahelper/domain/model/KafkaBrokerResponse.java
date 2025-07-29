package com.errday.kafkahelper.domain.model;

public record KafkaBrokerResponse(
        Long id,
        String alias,
        BootstrapServer bootstrapServer) {

    public static KafkaBrokerResponse from(KafkaBroker kafkaBroker) {
        return new KafkaBrokerResponse(kafkaBroker.getId(), kafkaBroker.getAlias(), new BootstrapServer(kafkaBroker.getHost(), kafkaBroker.getPort()));
    }

    public String host() { return bootstrapServer.host(); }
    public int port() { return bootstrapServer.port(); }
}
