package com.errday.kafkahelper.domain.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaBrokerTest {

    private KafkaBroker kafkaBroker;

    @BeforeEach
    void setUp() {
        String alias = "localhost";
        String host = "127.0.0.1";
        int port = 9092;

        KafkaBrokerRegisterRequest request = new KafkaBrokerRegisterRequest(alias, new BootstrapServer(host, port));
        kafkaBroker = KafkaBroker.of(request);
    }

    @Test
    void of() {
        String alias = "localhost";
        String host = "127.0.0.1";
        int port = 9092;

        KafkaBrokerRegisterRequest request = new KafkaBrokerRegisterRequest(alias, new BootstrapServer(host, port));
        KafkaBroker kafkaBroker = KafkaBroker.of(request);

        assertThat(kafkaBroker.getAlias()).isEqualTo(alias);
        assertThat(kafkaBroker.getHost()).isEqualTo(host);
        assertThat(kafkaBroker.getPort()).isEqualTo(port);
    }

    @Test
    void updateAlias() {
        String newAlias = "secondServer";
        kafkaBroker.updateAlias(newAlias);
        assertThat(kafkaBroker.getAlias()).isEqualTo(newAlias);
    }

    @Test
    void updateHost() {
        String newHost = "kafka.com";
        kafkaBroker.updateHost(newHost);
        assertThat(kafkaBroker.getHost()).isEqualTo(newHost);
    }

    @Test
    void  updatePort() {
        int newPort = 10092;
        kafkaBroker.updatePort(newPort);
        assertThat(kafkaBroker.getPort()).isEqualTo(newPort);
    }
}