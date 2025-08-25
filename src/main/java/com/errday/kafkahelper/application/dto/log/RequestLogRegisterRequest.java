package com.errday.kafkahelper.application.dto.log;

public record RequestLogRegisterRequest(
        String ip,
        String url
) {
}
