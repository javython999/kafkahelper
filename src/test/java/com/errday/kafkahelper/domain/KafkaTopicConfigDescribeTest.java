package com.errday.kafkahelper.domain;

import com.errday.kafkahelper.adapter.out.kafka.util.KafkaFieldUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaTopicConfigDescribeTest {

    @Test
    void of() {
        String name = "compression.type";
        String camelCase = KafkaFieldUtils.dotCaseToCamelCase(name);
        String value = "producer";
        boolean isDefault = true;
        boolean isReadOnly = false;
        boolean isSensitive = false;

        KafkaTopicConfigDescribe configDescribe = KafkaTopicConfigDescribe.of(name, value, camelCase, isDefault, isReadOnly, isSensitive);

        assertThat(configDescribe).isNotNull();
        assertThat(configDescribe.getName()).isEqualTo(name);
        assertThat(configDescribe.getCamelCase()).isEqualTo(camelCase);
        assertThat(configDescribe.getValue()).isEqualTo(value);
        assertThat(configDescribe.isDefault()).isEqualTo(isDefault);
        assertThat(configDescribe.isReadOnly()).isEqualTo(isReadOnly);
        assertThat(configDescribe.isSensitive()).isEqualTo(isSensitive);

    }
}