package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.port.in.KafkaProducerPort;
import com.errday.kafkahelper.domain.model.RegisterRecordRequest;
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
    public ResponseEntity<String> registerRecord(@RequestBody RegisterRecordRequest request) {
        KafkaProducerService.registerRecord(request);
        return ResponseEntity.ok("registerRecord success");
    }


}
