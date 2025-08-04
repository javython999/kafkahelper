package com.errday.kafkahelper.domain.model;

import com.errday.kafkahelper.application.dto.BootstrapServer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BootstrapServerTest {


    @Test
    void address() {
        String host = "localhost";
        int port = 9092;

        BootstrapServer localhost = new BootstrapServer(host, port);

        assertThat(localhost.host()).isEqualTo(host);
        assertThat(localhost.port()).isEqualTo(port);
        assertThat(localhost.address()).isEqualTo(host + ":" + port);
    }
}