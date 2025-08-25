package com.errday.kafkahelper.application.port.out.log;

import com.errday.kafkahelper.domain.log.RequestLog;

public interface RequestLogRegisterPort {

    RequestLog save(RequestLog requestLog);
}
