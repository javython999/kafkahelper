package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.domain.service.KafkaBrokerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KafkaBrokerController {

    private final KafkaBrokerService kafkaBrokerService;

    @GetMapping("/kafka/brokers")
    public String brokers(Model model) {
        return "kafka/brokers/list";
    }
}
