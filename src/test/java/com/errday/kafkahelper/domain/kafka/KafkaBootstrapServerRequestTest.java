package com.errday.kafkahelper.domain.kafka;

import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaBootstrapServerRequestTest {


    @Test
    void address() {
        String host = "localhost";
        int port = 9092;

        KafkaBootstrapServerRequest localhost = new KafkaBootstrapServerRequest(host, port);

        assertThat(localhost.host()).isEqualTo(host);
        assertThat(localhost.port()).isEqualTo(port);
        assertThat(localhost.address()).isEqualTo(host + ":" + port);
    }
}