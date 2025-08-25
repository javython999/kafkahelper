package com.errday.kafkahelper.domain.kafka;

import lombok.Getter;

@Getter
public class KafkaBootStrapServer {

    private final String host;
    private final Integer port;

    private KafkaBootStrapServer(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public static KafkaBootStrapServer of(String host, Integer port) {
        return new KafkaBootStrapServer(host, port);
    }

    public String address() {
        return host + ":" + port;
    }
}
