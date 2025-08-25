package com.errday.kafkahelper.application.port.in.log;

import com.errday.kafkahelper.application.dto.log.RequestLogRegisterRequest;
import com.errday.kafkahelper.domain.log.RequestLog;

public interface RequestLogRegisterUseCase {

    RequestLog register(RequestLogRegisterRequest request);
}
