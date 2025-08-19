package com.errday.kafkahelper.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaBootStrapServerTest {

    @Test
    void of() {
        String host = "localhost";
        int port = 9092;

        KafkaBootStrapServer bootStrapServer = KafkaBootStrapServer.of(host, port);

        assertThat(bootStrapServer.getHost()).isEqualTo(host);
        assertThat(bootStrapServer.getPort()).isEqualTo(port);
    }

    @Test
    void address() {
        String host = "localhost";
        int port = 9092;

        KafkaBootStrapServer bootStrapServer = KafkaBootStrapServer.of(host, port);

        assertThat(bootStrapServer.address()).isEqualTo(host + ":" + port);
    }
}