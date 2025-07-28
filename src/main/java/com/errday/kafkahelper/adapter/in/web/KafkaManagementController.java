package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.domain.model.KafkaBrokerResponse;
import com.errday.kafkahelper.domain.service.KafkaBrokerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KafkaManagementController {

    private final KafkaBrokerService kafkaBrokerService;

    @GetMapping("/kafka/brokers")
    public String brokers(Model model) {

        List<KafkaBrokerResponse> kafkaBrokers = kafkaBrokerService.findAll()
                .stream()
                .map(KafkaBrokerResponse::from)
                .toList();

        model.addAttribute("kafkaBrokers", kafkaBrokers);

        return "kafka/brokers/list";
    }
}
