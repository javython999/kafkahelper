package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.port.in.KafkaTopicPort;
import com.errday.kafkahelper.domain.model.BootstrapServer;
import com.errday.kafkahelper.domain.model.KafkaBrokerResponse;
import com.errday.kafkahelper.domain.model.TopicConfigDescribeRequest;
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
    private final KafkaTopicPort kafkaTopicService;
    private final List<String> configOptions = List.of(
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
    );

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
        model.addAttribute("topicConfig", kafkaTopicService.describeTopicConfig(
                new TopicConfigDescribeRequest(
                        bootstrapServer.host(),
                        bootstrapServer.port(),
                        topicName)).data());

        model.addAttribute("configOptions", configOptions);

        return "kafka/topics/edit";
    }

    @GetMapping("/kafka/topics/{topicName}/record")
    public String topicRecord(Model model, @PathVariable String topicName, BootstrapServer bootstrapServer) {
        model.addAttribute("bootstrapServer", bootstrapServer);
        model.addAttribute("topicName", topicName);


        return "kafka/topics/record";
    }
}