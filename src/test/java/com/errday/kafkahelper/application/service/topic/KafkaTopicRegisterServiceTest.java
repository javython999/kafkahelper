package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.out.kafka.topic.KafkaTopicRegisterPort;
import com.errday.kafkahelper.application.service.kafka.topic.KafkaTopicRegisterService;
import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.kafka.KafkaTopic;
import com.errday.kafkahelper.domain.kafka.KafkaTopicConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class KafkaTopicRegisterServiceTest {

    @Mock
    private KafkaTopicRegisterPort  kafkaTopicRegisterPort;

    @InjectMocks
    private KafkaTopicRegisterService kafkaTopicRegisterService;

    @Test
    void register() {

        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of("localhost", 9092),
                "mockTopic"
        );

        when(kafkaTopicRegisterPort.save(any(KafkaTopic.class)))
                .thenReturn(kafkaTopic);

        KafkaTopicRequest request = new KafkaTopicRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                1,
                (short) 1,
                new KafkaTopicConfig()
        );

        KafkaTopicResponse response = kafkaTopicRegisterService.register(request);
        assertThat(response).isNotNull();
        assertThat(response.topicName()).isEqualTo(request.topicName());
    }
}