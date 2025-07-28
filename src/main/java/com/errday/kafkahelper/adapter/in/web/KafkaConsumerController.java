package com.errday.kafkahelper.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KafkaConsumerController {

    @GetMapping("/consumer")
    public String consumer() {
        return "kafka/consumer/topics";
    }
}
