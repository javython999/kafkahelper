package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.port.in.KafkaProducerPort;
import com.errday.kafkahelper.adapter.in.web.dto.ApiResponse;
import com.errday.kafkahelper.adapter.in.web.dto.RegisterRecordRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaProducerApi {

    private final KafkaProducerPort KafkaProducerService;

    @PostMapping("/publish")
    public ResponseEntity<ApiResponse<String>> registerRecord(@RequestBody RegisterRecordRequest request) {
        try {
            KafkaProducerService.registerRecord(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            ResponseEntity.ok(ApiResponse.success("message sent fail", "error"));
        }
        return ResponseEntity.ok(ApiResponse.success("message sent successfully", "success"));
    }


}
