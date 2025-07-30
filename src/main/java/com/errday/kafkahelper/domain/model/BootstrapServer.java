package com.errday.kafkahelper.domain.model;

public record BootstrapServer(String host, Integer port) {

    public String address() {
        return host + ":" + port;
    }
}
