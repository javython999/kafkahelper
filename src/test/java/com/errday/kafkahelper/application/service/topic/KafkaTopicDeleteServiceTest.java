package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicRequest;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicDeletePort;
import com.errday.kafkahelper.application.service.kafka.topic.KafkaTopicDeleteService;
import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import com.errday.kafkahelper.domain.kafka.KafkaTopicConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaTopicDeleteServiceTest {

    @Mock
    private KafkaTopicDeletePort kafkaTopicDeletePort;

    @InjectMocks
    private KafkaTopicDeleteService kafkaTopicDeleteService;

    @Test
    void delete() {

        KafkaTopicRequest request = new KafkaTopicRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                1,
                (short) 0,
                new KafkaTopicConfig()
        );

        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of("localhost", 9092),
                "mockTopic"
        );

        when(kafkaTopicDeletePort.delete(any(KafkaTopic.class)))
                .thenReturn(kafkaTopic);

        assertThat(kafkaTopicDeleteService.delete(request).topicName())
                .isEqualTo(request.topicName());
    }


}