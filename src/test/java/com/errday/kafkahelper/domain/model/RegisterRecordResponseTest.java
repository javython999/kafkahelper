package com.errday.kafkahelper.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterRecordResponseTest {

    @Test
    void registerRecordResponse() {
        String topicName = "topic1";
        int partition = 1;
        long offset = 1L;
        String key = "name";
        String message = "kafkaMessage";

        RegisterRecordResponse response = new RegisterRecordResponse(topicName, partition, offset, key, message);

        assertThat(response.topicName()).isEqualTo(topicName);
        assertThat(response.partition()).isEqualTo(partition);
        assertThat(response.offset()).isEqualTo(offset);
        assertThat(response.key()).isEqualTo(key);
        assertThat(response.message()).isEqualTo(message);
    }

    @Test
    void empty() {
        RegisterRecordResponse empty = RegisterRecordResponse.empty();

        assertThat(empty.topicName()).isNull();
        assertThat(empty.partition()).isNull();
        assertThat(empty.offset()).isNull();
        assertThat(empty.key()).isNull();
        assertThat(empty.message()).isNull();
    }
}