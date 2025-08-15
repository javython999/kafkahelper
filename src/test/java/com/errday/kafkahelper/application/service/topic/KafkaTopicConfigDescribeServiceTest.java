package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicConfigDescribeResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicConfigDescribePort;
import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicConfig;
import com.errday.kafkahelper.domain.KafkaTopicConfigDescribe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaTopicConfigDescribeServiceTest {

    @Mock
    private KafkaTopicConfigDescribePort kafkaTopicConfigDescribePort;

    @InjectMocks
    private KafkaTopicConfigDescribeService kafkaTopicConfigDescribeService;


    @Test
    void configDescribe() {

        KafkaTopicRequest request = new KafkaTopicRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                1,
                (short) 0,
                new KafkaTopicConfig()
        );

        KafkaTopicConfigDescribe configDescribe0 = KafkaTopicConfigDescribe.of("config.name0", "configName0", "configValue0", true, true, true);
        KafkaTopicConfigDescribe configDescribe1 = KafkaTopicConfigDescribe.of("config.name1", "configName1", "configValue1", true, true, true);
        KafkaTopicConfigDescribe configDescribe2 = KafkaTopicConfigDescribe.of("config.name2", "configName2", "configValue2", true, true, true);

        when(kafkaTopicConfigDescribePort.configDescribe(any(KafkaTopic.class)))
                .thenReturn(List.of(configDescribe0, configDescribe1, configDescribe2));

        List<KafkaTopicConfigDescribeResponse> configDescribes = kafkaTopicConfigDescribeService.configDescribe(request);

        assertThat(configDescribes).hasSize(3);
        assertThat(configDescribes).containsExactly(
                KafkaTopicConfigDescribeResponse.from(configDescribe0),
                KafkaTopicConfigDescribeResponse.from(configDescribe1),
                KafkaTopicConfigDescribeResponse.from(configDescribe2)
        );
    }

    @Test
    void configDescribeExecutionException() {
        KafkaTopicRequest request = new KafkaTopicRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                1,
                (short) 0,
                new KafkaTopicConfig()
        );

        when(kafkaTopicConfigDescribePort.configDescribe(any()))
                .thenThrow(new RuntimeException(new ExecutionException("ExecutionExceptionTest", null)));

        assertThatThrownBy(() -> kafkaTopicConfigDescribeService.configDescribe(request))
                .isInstanceOf(RuntimeException.class)
                .hasCauseInstanceOf(ExecutionException.class);
    }

    @Test
    void configDescribeInterruptedException() {
        KafkaTopicRequest request = new KafkaTopicRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                1,
                (short) 0,
                new KafkaTopicConfig()
        );

        when(kafkaTopicConfigDescribePort.configDescribe(any()))
                .thenThrow(new RuntimeException(new InterruptedException()));

        assertThatThrownBy(() -> kafkaTopicConfigDescribeService.configDescribe(request))
                .isInstanceOf(RuntimeException.class)
                .hasCauseInstanceOf(InterruptedException.class);

    }

}