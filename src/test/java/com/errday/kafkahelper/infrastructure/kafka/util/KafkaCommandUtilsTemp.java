package com.errday.kafkahelper.infrastructure.kafka.util;

import com.errday.kafkahelper.adapter.out.kafka.util.KafkaCommandUtils;
import com.errday.kafkahelper.domain.model.TopicAlterRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaCommandUtilsTemp {

    @Test
    void getNonNullFields() {
        TopicAlterRequest testCase = new TopicAlterRequest(
                604800000L,
                1073741824L,
                null,
                1073741824L,
                null,
                604800000L,
                0.5F,
                null,
                10000,
                1000,
                "CreateTime",
                "gzip"
        );

        Map<String, String> actual = KafkaCommandUtils.getNonNullFields(testCase);

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