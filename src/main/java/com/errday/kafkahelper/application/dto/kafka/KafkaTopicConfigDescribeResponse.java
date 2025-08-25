package com.errday.kafkahelper.application.dto.kafka;

import com.errday.kafkahelper.domain.kafka.KafkaTopicConfigDescribe;

public record KafkaTopicConfigDescribeResponse(
        String name,
        String camelCase,
        String value,
        boolean isDefault,
        boolean isReadOnly,
        boolean isSensitive) {

    public static KafkaTopicConfigDescribeResponse from(KafkaTopicConfigDescribe configDescribe) {
        return new KafkaTopicConfigDescribeResponse(
                configDescribe.getName(),
                configDescribe.getCamelCase(),
                configDescribe.getValue(),
                configDescribe.isDefault(),
                configDescribe.isReadOnly(),
                configDescribe.isSensitive()
        );
    }
}
