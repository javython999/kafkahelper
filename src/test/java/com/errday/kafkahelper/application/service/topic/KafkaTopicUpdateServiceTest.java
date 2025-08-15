package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicUpdatePort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class KafkaTopicUpdateServiceTest {

    @Mock
    private KafkaTopicUpdatePort kafkaTopicUpdatePort;

    @InjectMocks
    private KafkaTopicUpdateService kafkaTopicUpdateService;

    @Test
    void update() {

        KafkaTopic kafkaTopic = KafkaTopic.of(
                KafkaBootStrapServer.of("localhost", 9092),
                "mockTopic"
        );

        when(kafkaTopicUpdatePort.update(any(KafkaTopic.class)))
                .thenReturn(kafkaTopic);

        KafkaTopicRequest request = new KafkaTopicRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                1,
                (short) 1,
                new KafkaTopicConfig()
        );

        KafkaTopicResponse response = kafkaTopicUpdateService.update(request);

        assertThat(response).isNotNull();
        assertThat(response.topicName())
                .isEqualTo(kafkaTopic.getTopicName());
    }
}