package com.errday.kafkahelper.domain.log;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestLog {

    private Long id;
    private String ip;
    private String url;
    private LocalDateTime requestTime;

    public static RequestLog of(String ip, String url, LocalDateTime requestTime) {
        return new RequestLog(null, ip, url, requestTime);
    }
}
