package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.dto.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.in.broker.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaBrokerApi {

    private final KafkaBrokerRegisterUseCase kafkaBrokerRegisterUseCase;
    private final KafkaBrokerListUseCase kafkaBrokerListUseCase;
    private final KafkaBrokerFindUseCase kafkaBrokerFindUseCase;
    private final KafkaBrokerUpdateUseCase kafkaBrokerUpdateUseCase;
    private final KafkaBrokerDeleteUseCase kafkaBrokerDeleteUseCase;

    @GetMapping("/brokers")
    public ResponseEntity<List<KafkaBrokerResponse>> listBrokers() {
        return ResponseEntity.ok(kafkaBrokerListUseCase.findAll());
    }

    @PostMapping("/brokers")
    public ResponseEntity<KafkaBrokerResponse> registerBroker(@RequestBody KafkaBrokerRegisterRequest request) {
        return ResponseEntity.ok(kafkaBrokerRegisterUseCase.register(request));
    }

    @GetMapping("/brokers/{brokerId}")
    public ResponseEntity<KafkaBrokerResponse> brokerDetail(@PathVariable Long brokerId) {
        return ResponseEntity.ok(kafkaBrokerFindUseCase.findById(brokerId));
    }

    @PutMapping("/brokers/{brokerId}")
    public ResponseEntity<KafkaBrokerResponse> updateBroker(@PathVariable Long brokerId, @RequestBody KafkaBrokerUpdateRequest request) {
        KafkaBrokerResponse findById = kafkaBrokerFindUseCase.findById(brokerId);

        if (!findById.alias().equals(request.oldAlias())) {
            return ResponseEntity.badRequest().build();
        }

        if (!findById.host().equals(request.oldHost())) {
            return ResponseEntity.badRequest().build();
        }

        if (findById.port() != request.oldPort()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(kafkaBrokerUpdateUseCase.update(request));
    }

    @DeleteMapping("/brokers/{brokerId}")
    public ResponseEntity<Boolean> deleteBroker(@PathVariable Long brokerId) {
        return ResponseEntity.ok(kafkaBrokerDeleteUseCase.deleteById(brokerId));
    }


}
