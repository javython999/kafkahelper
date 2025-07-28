package com.errday.kafkahelper.domain.model;

public record KafkaBrokerResponse(
        Long id,
        String alias,
        String host,
        Integer port) {

    public static KafkaBrokerResponse from(KafkaBroker kafkaBroker) {
        return new KafkaBrokerResponse(kafkaBroker.getId(), kafkaBroker.getAlias(), kafkaBroker.getHost(), kafkaBroker.getPort());
    }
}
