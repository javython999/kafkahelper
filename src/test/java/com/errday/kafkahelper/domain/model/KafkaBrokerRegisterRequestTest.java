package com.errday.kafkahelper.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaBrokerRegisterRequestTest {

    @Test
    void kafkaBrokerRegisterRequest() {
        String alias = "localhost";
        String host = "127.0.0.1";
        Integer port = 9092;

        KafkaBrokerRegisterRequest request = new KafkaBrokerRegisterRequest(alias, new BootstrapServer(host, port));

        assertThat(request.alias()).isEqualTo(alias);
        assertThat(request.host()).isEqualTo(host);
        assertThat(request.port()).isEqualTo(port);
    }
}