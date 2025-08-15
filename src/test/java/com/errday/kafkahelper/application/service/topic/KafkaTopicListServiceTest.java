package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicResponse;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicListPort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaTopic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaTopicListServiceTest {

    @Mock
    private KafkaTopicListPort kafkaTopicListPort;

    @InjectMocks
    private KafkaTopicListService kafkaTopicListService;

    @Test
    void findAll() {

        KafkaBootstrapServerRequest request = new KafkaBootstrapServerRequest("localhost", 9092);

        List<KafkaTopic> kafkaTopics = List.of(
                KafkaTopic.of(KafkaBootStrapServer.of("localhost", 9092), "mockTopic1"),
                KafkaTopic.of(KafkaBootStrapServer.of("localhost", 9092), "mockTopic2")
        );

        when(kafkaTopicListPort.findAll(any(KafkaBootStrapServer.class)))
                .thenReturn(kafkaTopics);

        List<KafkaTopicResponse> topics = kafkaTopicListService.findAll(request);

        assertThat(topics).hasSize(kafkaTopics.size());
        assertThat(topics).extracting(KafkaTopicResponse::topicName)
                .containsExactlyInAnyOrder("mockTopic1", "mockTopic2");
    }

}