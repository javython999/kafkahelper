package com.errday.kafkahelper.application.service.log;

import com.errday.kafkahelper.application.dto.log.RequestLogRegisterRequest;
import com.errday.kafkahelper.application.port.in.log.RequestLogRegisterUseCase;
import com.errday.kafkahelper.application.port.out.log.RequestLogRegisterPort;
import com.errday.kafkahelper.domain.log.RequestLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RequestLogRegisterService implements RequestLogRegisterUseCase {

    private final RequestLogRegisterPort requestLogRegisterPort;

    @Override
    public RequestLog register(RequestLogRegisterRequest request) {
        return requestLogRegisterPort.save(RequestLog.of(request.ip(), request.url(), LocalDateTime.now()));
    }
}
