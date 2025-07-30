package com.errday.kafkahelper.domain.model;

public record TopicConfigDescribe(
        String name,
        String dotCaseName,
        String value,
        boolean isDefault,
        boolean isReadOnly,
        boolean isSensitive) {
}
