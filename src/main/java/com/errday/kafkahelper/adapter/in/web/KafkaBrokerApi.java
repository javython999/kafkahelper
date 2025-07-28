package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.domain.model.KafkaBroker;
import com.errday.kafkahelper.domain.model.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.domain.model.KafkaBrokerResponse;
import com.errday.kafkahelper.domain.model.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.domain.service.KafkaBrokerService;
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

    private final KafkaBrokerService kafkaBrokerService;

    @GetMapping("/brokers")
    public ResponseEntity<List<KafkaBrokerResponse>> listBrokers() {
        List<KafkaBrokerResponse> brokers = kafkaBrokerService.findAll().stream()
                .map(KafkaBrokerResponse::from)
                .toList();

        return ResponseEntity.ok(brokers);
    }

    @PostMapping("/brokers")
    public ResponseEntity<KafkaBrokerResponse> registerBroker(@RequestBody KafkaBrokerRegisterRequest request) {
        KafkaBroker saved = kafkaBrokerService.save(request);
        return ResponseEntity.ok(KafkaBrokerResponse.from(saved));
    }

    @GetMapping("/brokers/{brokerId}")
    public ResponseEntity<KafkaBrokerResponse> brokerDetail(@PathVariable Long brokerId) {
        return ResponseEntity.ok(KafkaBrokerResponse.from(kafkaBrokerService.findById(brokerId)));
    }

    @PutMapping("/brokers/{brokerId}")
    public ResponseEntity<KafkaBrokerResponse> updateBroker(@PathVariable Long brokerId, @RequestBody KafkaBrokerUpdateRequest request) {
        KafkaBroker broker = kafkaBrokerService.findById(brokerId);

        if (!broker.getAlias().equals(request.oldAlias())) {
            return ResponseEntity.badRequest().build();
        }

        if (!broker.getHost().equals(request.oldHost())) {
            return ResponseEntity.badRequest().build();
        }

        if (!broker.getPort().equals(request.oldPort())) {
            return ResponseEntity.badRequest().build();
        }

        KafkaBroker updated = kafkaBrokerService.update(request);
        return ResponseEntity.ok(KafkaBrokerResponse.from(updated));
    }

    @DeleteMapping("/brokers/{brokerId}")
    public ResponseEntity<Boolean> deleteBroker(@PathVariable Long brokerId) {
        return ResponseEntity.ok(kafkaBrokerService.deleteById(brokerId));
    }


}
