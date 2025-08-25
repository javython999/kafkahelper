package com.errday.kafkahelper.adapter.in.web.interceptor;

import com.errday.kafkahelper.application.port.out.log.RequestLogRegisterPort;
import com.errday.kafkahelper.domain.log.RequestLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RequestLogRegisterInterceptor implements HandlerInterceptor {

    private final RequestLogRegisterPort requestLogRegisterPort;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {

        RequestLog requestLogRegisterRequest = RequestLog.of(
                request.getRemoteAddr(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        requestLogRegisterPort.save(requestLogRegisterRequest);

        return true;
    }
}
