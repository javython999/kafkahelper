package com.errday.kafkahelper.adapter.in.web;

import com.errday.kafkahelper.adapter.in.web.dto.ApiResponse;
import com.errday.kafkahelper.application.dto.kafka.*;
import com.errday.kafkahelper.application.port.in.kafka.topic.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaTopicApi {

    private final KafkaTopicRegisterUseCase kafkaTopicRegisterUseCase;
    private final KafkaTopicDescribeUseCase kafkaTopicDescribeUseCase;
    private final KafkaTopicListUseCase kafkaTopicListUseCase;
    private final KafkaTopicConfigDescribeUseCase kafkaTopicConfigDescribeUseCase;
    private final KafkaTopicUpdateUseCase kafkaTopicUpdateUseCase;
    private final KafkaTopicDeleteUseCase kafkaTopicDeleteUseCase;

    @PostMapping("/topics")
    public ApiResponse<KafkaTopicResponse> registerTopic(@RequestBody KafkaTopicRequest request) {

        KafkaTopicResponse saved = kafkaTopicRegisterUseCase.register(request);
        if (saved == null) {
            return ApiResponse.error("error", null);
        }

        return ApiResponse.success("success", saved);
    }

    @PostMapping("/topics/{topicName}")
    public ApiResponse<KafkaTopicDescribeResponse> describeTopic(@RequestBody KafkaTopicRequest request) {

        KafkaTopicDescribeResponse describe = kafkaTopicDescribeUseCase.describe(request);
        if (describe == null) {
            return ApiResponse.error("error", null);
        }

        return ApiResponse.success("success", describe);
    }

    @GetMapping("/topics")
    public ApiResponse<List<KafkaTopicResponse>> topicList(KafkaBootstrapServerRequest kafkaBootstrapServerRequest) {

        List<KafkaTopicResponse> findAll = kafkaTopicListUseCase.findAll(kafkaBootstrapServerRequest);
        if (findAll == null) {
            return ApiResponse.error("error", null);
        }

        return ApiResponse.success("success", findAll);
    }

    @PostMapping("/topics/{topicName}/configs")
    public ApiResponse<List<KafkaTopicConfigDescribeResponse>> topiConfigs(@RequestBody KafkaTopicRequest request) {

        List<KafkaTopicConfigDescribeResponse> configDescribes = kafkaTopicConfigDescribeUseCase.configDescribe(request);
        if (configDescribes == null) {
            return ApiResponse.error("error", null);
        }

        return ApiResponse.success("success", configDescribes);
    }

    @PatchMapping("/topics/{topicName}")
    public ApiResponse<KafkaTopicResponse> updateTopic(@RequestBody KafkaTopicRequest request) {

        KafkaTopicResponse updated = kafkaTopicUpdateUseCase.update(request);
        if (updated == null) {
            return ApiResponse.error("error", null);
        }

        return ApiResponse.success("success", updated);
    }

    @DeleteMapping("/topics/{topicName}")
    public ApiResponse<KafkaTopicResponse> deleteTopic(@RequestBody KafkaTopicRequest request) {

        KafkaTopicResponse deleted = kafkaTopicDeleteUseCase.delete(request);
        if (deleted == null) {
            return ApiResponse.error("error", null);
        }

        return ApiResponse.success("success", deleted);
    }

}
