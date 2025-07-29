package com.errday.kafkahelper.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterRecordRequestTest {

    @Test
    void registerRecordRequest() {
        String topicName = "testTopic";
        String key = "name";
        String message = "kafka";

        RegisterRecordRequest request = new RegisterRecordRequest(topicName, key, message);

        assertThat(request.topicName()).isEqualTo(topicName);
        assertThat(request.key()).isEqualTo(key);
        assertThat(request.message()).isEqualTo(message);
    }
}