package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.port.in.KafkaTopicPort;
import com.errday.kafkahelper.domain.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaTopicApi {

    private final KafkaTopicPort kafkaTopicService;

    @PostMapping("/topics")
    public ResponseEntity<ApiResponse<String>> createTopic(@RequestBody TopicCreateRequest request) {
        return ResponseEntity.ok(kafkaTopicService.createTopic(request));
    }

    @GetMapping("/topics/{topicName}")
    public TopicDescribe describeTopic(@PathVariable String topicName) {
        return kafkaTopicService.describeTopic(topicName);
    }

    @GetMapping("/topics")
    public Set<String> topicList(BootstrapServer bootstrapServer) {
        return kafkaTopicService.topicList(bootstrapServer);
    }

    @GetMapping("/topics/{topicName}/configs")
    public List<TopicConfigDescribe> topiConfigs(@PathVariable String topicName) {
        return kafkaTopicService.describeTopicConfig(topicName);
    }

    @PatchMapping("/topics/{topicName}")
    public String updateTopic(@PathVariable String topicName, @RequestBody TopicAlterRequest config) {
        return kafkaTopicService.updateTopicConfig(topicName, config);
    }

    @DeleteMapping("/topics/{topicName}")
    public String deleteTopic(@PathVariable String topicName) {
        return kafkaTopicService.deleteTopic(topicName);
    }

}
