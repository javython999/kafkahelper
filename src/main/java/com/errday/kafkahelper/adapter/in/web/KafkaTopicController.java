package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.service.KafkaTopicService;
import com.errday.kafkahelper.domain.model.BootstrapServer;
import com.errday.kafkahelper.domain.model.KafkaBrokerResponse;
import com.errday.kafkahelper.domain.service.KafkaBrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KafkaTopicController {

    private final KafkaBrokerService kafkaBrokerService;
    private final KafkaTopicService kafkaTopicService;

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

    @GetMapping("/kafka/topics/{topicName}/edit")
    public String topicEdit(Model model, @PathVariable String topicName, BootstrapServer bootstrapServer) {
        model.addAttribute("bootstrapServer", bootstrapServer);
        model.addAttribute("topicName", topicName);
        model.addAttribute("topicConfig", kafkaTopicService.describeTopicConfig(topicName));

        model.addAttribute("configOptions", List.of(
                        "retention.ms",
                        "retention.bytes",
                        "cleanup.policy",
                        "max.message.bytes",
                        "segment.bytes",
                        "segment.ms",
                        "min.cleanable.dirty.ratio",
                        "delete.retention.ms",
                        "flush.messages",
                        "flush.ms",
                        "message.timestamp.type",
                        "compression.type"
                )
        );

        return "kafka/topics/edit";
    }
}