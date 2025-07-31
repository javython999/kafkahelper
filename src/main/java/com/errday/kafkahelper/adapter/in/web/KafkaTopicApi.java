package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.application.port.in.KafkaTopicPort;
import com.errday.kafkahelper.domain.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/topics/{topicName}")
    public ApiResponse<TopicDescribe> describeTopic(@RequestBody TopicDescribeRequest request) {
        return kafkaTopicService.describeTopic(request);
    }

    @GetMapping("/topics")
    public ResponseEntity<ApiResponse<List<String>>> topicList(BootstrapServer bootstrapServer) {
        return ResponseEntity.ok(kafkaTopicService.topicList(bootstrapServer));
    }

    @GetMapping("/topics/{topicName}/configs")
    public ResponseEntity<ApiResponse<List<TopicConfigDescribe>>> topiConfigs(TopicConfigDescribeRequest request) {
        return ResponseEntity.ok(kafkaTopicService.describeTopicConfig(request));
    }

    @PatchMapping("/topics/{topicName}")
    public ResponseEntity<ApiResponse<String>> updateTopic(@PathVariable String topicName, @RequestBody TopicEditRequest config) {
        return ResponseEntity.ok(kafkaTopicService.updateTopicConfig(topicName, config));
    }

    @DeleteMapping("/topics/{topicName}")
    public ResponseEntity<ApiResponse<String>> deleteTopic(@RequestBody TopicDeleteRequest request) {
        return ResponseEntity.ok(kafkaTopicService.deleteTopic(request));
    }

}
