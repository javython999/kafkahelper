package com.errday.kafkahelper.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterRecordRequestTest {

    @Test
    void registerRecordRequest() {
        String host = "localhost";
        int port = 9092;
        String topicName = "testTopic";
        String key = "name";
        String message = "kafka";

        RegisterRecordRequest request = new RegisterRecordRequest(new BootstrapServer(host, port), topicName, key, message);

        assertThat(request.topicName()).isEqualTo(topicName);
        assertThat(request.key()).isEqualTo(key);
        assertThat(request.message()).isEqualTo(message);
    }
}