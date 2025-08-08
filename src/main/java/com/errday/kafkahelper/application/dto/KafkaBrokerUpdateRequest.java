package com.errday.kafkahelper.application.dto;

public record KafkaBrokerUpdateRequest(
        Long id,
        String alias,
        String host,
        Integer port,
        String oldAlias,
        String oldHost,
        Integer oldPort) {
}
