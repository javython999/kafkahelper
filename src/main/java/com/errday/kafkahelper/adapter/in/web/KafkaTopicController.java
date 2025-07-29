package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.domain.model.BootstrapServer;
import com.errday.kafkahelper.domain.model.KafkaBroker;
import com.errday.kafkahelper.domain.model.KafkaBrokerResponse;
import com.errday.kafkahelper.domain.service.KafkaBrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KafkaTopicController {

    private final KafkaBrokerService kafkaBrokerService;

    @GetMapping("/kafka/topics")
    public String topics(Model model, BootstrapServer bootstrapServer) {

        List<KafkaBrokerResponse> kafkaBrokers = kafkaBrokerService.findAll()
                .stream()
                .map(KafkaBrokerResponse::from)
                .toList();

        model.addAttribute("kafkaBrokers", kafkaBrokers);
        model.addAttribute("bootstrapServer", bootstrapServer);

        return "kafka/topics/list";
    }

    @PostMapping("/kafka/topics/write")
    public String topicWrite(Model model, BootstrapServer bootstrapServer) {
        model.addAttribute("bootstrapServer", bootstrapServer);
        return "kafka/topics/write";
    }
}