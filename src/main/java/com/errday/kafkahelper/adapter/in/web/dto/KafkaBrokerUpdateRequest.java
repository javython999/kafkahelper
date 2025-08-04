package com.errday.kafkahelper.adapter.in.web.dto;

public record KafkaBrokerUpdateRequest(
        Long id,
        String alias,
        String host,
        Integer port,
        String oldAlias,
        String oldHost,
        Integer oldPort) {
}
