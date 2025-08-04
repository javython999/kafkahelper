package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.adapter.out.kafka.sse.DynamicKafkaConsumerManager;
import com.errday.kafkahelper.adapter.out.kafka.sse.SseEmitters;
import com.errday.kafkahelper.adapter.in.web.dto.KafkaSseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/kafka/sse")
@RequiredArgsConstructor
public class KafkaSseController {

    private final SseEmitters sseEmitters;
    private final DynamicKafkaConsumerManager consumerManager;

    @GetMapping(value = "/subscribe/{topicName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable String topicName, @RequestParam String host, @RequestParam int port) {
        consumerManager.startListening(new KafkaSseRequest(topicName, host, port)); // 동적 리스너 생성
        return sseEmitters.createEmitter(topicName);
    }
}
