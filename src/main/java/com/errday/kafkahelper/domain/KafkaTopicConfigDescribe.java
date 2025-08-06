package com.errday.kafkahelper.domain;

import lombok.Getter;

@Getter
public class KafkaTopicConfigDescribe {

    private String name;
    private String camelCase;
    private String value;
    private boolean isDefault;
    private boolean isReadOnly;
    private boolean isSensitive;

    private KafkaTopicConfigDescribe(String name, String camelCase, String value, boolean isDefault, boolean isReadOnly, boolean isSensitive) {
        this.name = name;
        this.camelCase = camelCase;
        this.value = value;
        this.isDefault = isDefault;
        this.isReadOnly = isReadOnly;
        this.isSensitive = isSensitive;
    }

    public static KafkaTopicConfigDescribe of(String name, String camelCase, String value, boolean isDefault, boolean isReadOnly, boolean isSensitive) {
        return new KafkaTopicConfigDescribe(name, camelCase, value, isDefault, isReadOnly, isSensitive);
    }
}
