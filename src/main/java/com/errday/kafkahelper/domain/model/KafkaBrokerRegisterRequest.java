package com.errday.kafkahelper.domain.model;

public record KafkaBrokerRegisterRequest(
        String alias,
        String  host,
        Integer port) {
}
