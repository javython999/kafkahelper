package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.adapter.in.web.dto.ApiResponse;
import com.errday.kafkahelper.application.dto.KafkaRecordRegisterRequest;
import com.errday.kafkahelper.application.port.in.producer.KafkaRecordRegisterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaProducerApi {

    private final KafkaRecordRegisterUseCase kafkaRecordRegisterUseCase;

    @PostMapping("/publish")
    public ApiResponse<Boolean> registerRecord(@RequestBody KafkaRecordRegisterRequest request) {
        return kafkaRecordRegisterUseCase.register(request)
                ? ApiResponse.success("success", true)
                : ApiResponse.error("error", false);
    }


}
