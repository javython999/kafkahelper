package com.errday.kafkahelper.domain.kafka;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KafkaBrokerTest {

    @Test
    void of() {
        Long id = 1L;
        String alias = "local-kafka-broker";
        String host = "localhost";
        int port = 9092;

        KafkaBroker kafkaBroker = KafkaBroker.of(id, alias, host, port);

        assertThat(kafkaBroker.getId()).isEqualTo(id);
        assertThat(kafkaBroker.getAlias()).isEqualTo(alias);
        assertThat(kafkaBroker.getHost()).isEqualTo(host);
        assertThat(kafkaBroker.getPort()).isEqualTo(port);
    }

    @Test
    void ofWithNullAlias() {
        assertThatThrownBy(() -> KafkaBroker.of(1L, null, "localhost", 9092))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void ofWithNullHost() {
        assertThatThrownBy(() -> KafkaBroker.of(1L, "local-kafka-broker", null, 9092))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void ofWithNullPort() {
        assertThatThrownBy(() -> KafkaBroker.of(1L, "local-kafka-broker", "localhost", null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void update() {
        Long id = 1L;
        KafkaBroker kafkaBroker = KafkaBroker.of(id, "local-kafka-broker", "localhost", 9092);

        String newAlias = "new-local-kafka-broker";
        String newHost = "new-localhost";
        int newPort = 9093;

        kafkaBroker.update(newAlias, newHost, newPort);

        assertThat(kafkaBroker.getId()).isEqualTo(id);
        assertThat(kafkaBroker.getAlias()).isEqualTo(newAlias);
        assertThat(kafkaBroker.getHost()).isEqualTo(newHost);
        assertThat(kafkaBroker.getPort()).isEqualTo(newPort);
    }

    @Test
    void updateAlias() {
        KafkaBroker kafkaBroker = KafkaBroker.of(1L, "local-kafka-broker", "localhost", 9092);

        String newAlias = "new-local-kafka-broker";

        kafkaBroker.updateAlias(newAlias);

        assertThat(kafkaBroker.getAlias()).isEqualTo(newAlias);
    }

    @Test
    void updateHost() {
        KafkaBroker kafkaBroker = KafkaBroker.of(1L, "local-kafka-broker", "localhost", 9092);

        String newHost = "new-localhost";

        kafkaBroker.updateHost(newHost);

        assertThat(kafkaBroker.getHost()).isEqualTo(newHost);
    }

    @Test
    void updatePort() {
        KafkaBroker kafkaBroker = KafkaBroker.of(1L, "local-kafka-broker", "localhost", 9092);

        int newPort = 9093;

        kafkaBroker.updatePort(newPort);

        assertThat(kafkaBroker.getPort()).isEqualTo(newPort);
    }

}