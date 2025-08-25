package com.errday.kafkahelper.adapter.out.log;

import com.errday.kafkahelper.adapter.out.log.entity.RequestLogEntity;
import com.errday.kafkahelper.adapter.out.log.entity.RequestLogRepository;
import com.errday.kafkahelper.application.port.out.log.RequestLogRegisterPort;
import com.errday.kafkahelper.domain.log.RequestLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLogRegisterAdapter implements RequestLogRegisterPort {

    private final RequestLogRepository requestLogRepository;

    @Override
    public RequestLog save(RequestLog requestLog) {

        RequestLogEntity save = requestLogRepository.save(RequestLogEntity.from(requestLog));

        return save.toDomain();
    }
}
