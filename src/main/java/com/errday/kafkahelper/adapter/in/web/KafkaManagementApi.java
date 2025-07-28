package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.domain.model.KafkaBroker;
import com.errday.kafkahelper.domain.model.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.domain.model.KafkaBrokerResponse;
import com.errday.kafkahelper.domain.service.KafkaBrokerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaManagementApi {

    private final KafkaBrokerService kafkaBrokerService;

    @PostMapping("/management/brokers")
    public ResponseEntity<KafkaBrokerResponse> registerBroker(@RequestBody KafkaBrokerRegisterRequest request) {
        KafkaBroker saved = kafkaBrokerService.save(request);
        return ResponseEntity.ok(KafkaBrokerResponse.from(saved));
    }

}
