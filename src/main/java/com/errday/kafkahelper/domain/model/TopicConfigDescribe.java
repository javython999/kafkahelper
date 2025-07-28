package com.errday.kafkahelper.domain.model;

public record TopicConfigDescribe(
        String name,
        String value,
        boolean isDefault,
        boolean isReadOnly,
        boolean isSensitive) {
}
