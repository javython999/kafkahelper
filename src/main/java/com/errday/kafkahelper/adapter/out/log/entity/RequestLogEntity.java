package com.errday.kafkahelper.adapter.out.log.entity;

import com.errday.kafkahelper.domain.log.RequestLog;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "request_log")
public class RequestLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private String url;
    private LocalDateTime requestTime;

    public static RequestLogEntity from(RequestLog requestLog) {
        return new RequestLogEntity(requestLog.getId(), requestLog.getIp(), requestLog.getUrl(), requestLog.getRequestTime());
    }

    public RequestLog toDomain() {
        return RequestLog.of(ip, url, requestTime);
    }
}
