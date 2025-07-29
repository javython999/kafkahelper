package com.errday.kafkahelper.domain.model;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaBrokerResponseTest {

    @Test
    void from() {
        long id = 1L;
        String alias = "localhost";
        String host = "127.0.0.1";
        int port = 9092;
        KafkaBroker kafkaBroker = KafkaBrokerFixture.kafkaBroker(id, alias, host, port);

        KafkaBrokerResponse from = KafkaBrokerResponse.from(kafkaBroker);

        assertThat(from.id()).isNotNull();
        assertThat(from.alias()).isEqualTo(kafkaBroker.getAlias());
        assertThat(from.host()).isEqualTo(kafkaBroker.getHost());
        assertThat(from.port()).isEqualTo(kafkaBroker.getPort());
    }
}