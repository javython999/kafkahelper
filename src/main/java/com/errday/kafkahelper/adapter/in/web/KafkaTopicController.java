package com.errday.kafkahelper.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KafkaTopicController {

    @GetMapping("/kafka/topics")
    public String topics() {
        return "kafka/topics/list";
    }
}