package com.errday.kafkahelper.domain.model;

public record BootstrapServer(String host, int port) {

    public String address() {
        return host + ":" + port;
    }
}
