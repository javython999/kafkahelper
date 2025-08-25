package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.port.in.kafka.broker.KafkaBrokerListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaBrokerListUseCase kafkaBrokerListUseCase;

    @ModelAttribute
    public void setPageData(Model model) {
        model.addAttribute("module", "producer");
        model.addAttribute("title", "producer");
    }

    @GetMapping("/kafka/producer")
    public String producer(Model model) {
        model.addAttribute("kafkaBrokers", kafkaBrokerListUseCase.findAll());
        return "kafka/producer/list";
    }
}