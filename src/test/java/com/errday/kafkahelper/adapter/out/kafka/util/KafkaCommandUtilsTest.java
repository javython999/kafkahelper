package com.errday.kafkahelper.adapter.out.kafka.util;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.adapter.in.web.dto.TopicEditConfig;
import com.errday.kafkahelper.adapter.in.web.dto.TopicEditRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaCommandUtilsTest {

    @Test
    void getNonNullFields() {
        TopicEditRequest testCase = new TopicEditRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                new TopicEditConfig(604800000L,
                        1073741824L,
                        null,
                        1073741824L,
                        null,
                        604800000L,
                        0.5F,
                        null,
                        10000L,
                        1000L,
                        "CreateTime",
                        "gzip")
        );

        Map<String, String> actual = KafkaFieldUtils.getNonNullFields(testCase.config());

        assertThat(actual)
                .containsEntry("retention.ms", "604800000")
                .containsEntry("retention.bytes", "1073741824")
                .containsEntry("max.message.bytes", "1073741824")
                .containsEntry("segment.ms", "604800000")
                .containsEntry("min.cleanable.dirty.ratio", "0.5")
                .containsEntry("flush.messages", "10000")
                .containsEntry("flush.ms", "1000")
                .containsEntry("message.timestamp.type", "CreateTime")
                .containsEntry("compression.type", "gzip");


        assertThat(actual)
                .doesNotContainKey("cleanup.policy")
                .doesNotContainKey("segment.bytes")
                .doesNotContainKey("delete.retention.ms");
    }
}