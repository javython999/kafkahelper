package com.errday.kafkahelper.adapter.in.web.dto;

public record TopicConfigDescribe(
        String name,
        String camelCase,
        String value,
        boolean isDefault,
        boolean isReadOnly,
        boolean isSensitive) {
}
