package com.errday.kafkahelper.application.dto;

public record BootstrapServer(String host, Integer port) {

    public String address() {
        return host + ":" + port;
    }
}
