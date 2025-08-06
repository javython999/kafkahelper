package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.adapter.in.web.dto.TopicConfigDescribeRequest;
import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.port.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KafkaTopicController {

    private final KafkaBrokerListUseCase kafkaBrokerListUseCase;
    private final KafkaTopicConfigDescribeUseCase kafkaTopicConfigDescribeUseCase;
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

    @ModelAttribute
    public void setPageData(Model model) {
        model.addAttribute("module", "topics");
        model.addAttribute("title", "topics");
    }

    @GetMapping("/kafka/topics")
    public String topics(Model model, KafkaBootstrapServerRequest kafkaBootstrapServerRequest) {

        List<KafkaBrokerResponse> kafkaBrokers = kafkaBrokerListUseCase.findAll();

        model.addAttribute("kafkaBrokers", kafkaBrokers);
        model.addAttribute("bootstrapServer", kafkaBootstrapServerRequest);

        return "kafka/topics/list";
    }

    @PostMapping("/kafka/topics/write")
    public String topicWrite(Model model, KafkaBootstrapServerRequest kafkaBootstrapServerRequest) {
        model.addAttribute("bootstrapServer", kafkaBootstrapServerRequest);
        return "kafka/topics/write";
    }

    @GetMapping("/kafka/topics/{topicName}/edit")
    public String topicEdit(@PathVariable String topicName, Model model, KafkaBootstrapServerRequest kafkaBootstrapServerRequest) {
        model.addAttribute("bootstrapServer", kafkaBootstrapServerRequest);
        model.addAttribute("topicName", topicName);
        model.addAttribute(
                "topicConfig",
                kafkaTopicConfigDescribeUseCase.configDescribe(
                        new KafkaTopicRequest(
                                kafkaBootstrapServerRequest,
                                topicName,
                                null,
                                null,
                                null)
                )
        );

        model.addAttribute("configOptions", configOptions);

        return "kafka/topics/edit";
    }

    @GetMapping("/kafka/topics/{topicName}/record")
    public String topicRecord(@PathVariable String topicName, Model model, KafkaBootstrapServerRequest kafkaBootstrapServerRequest) {
        model.addAttribute("bootstrapServer", kafkaBootstrapServerRequest);
        model.addAttribute("topicName", topicName);

        return "kafka/topics/record";
    }
}