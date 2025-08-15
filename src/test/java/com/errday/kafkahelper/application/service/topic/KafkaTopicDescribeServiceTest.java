package com.errday.kafkahelper.application.service.topic;

import com.errday.kafkahelper.application.dto.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.KafkaTopicDescribeResponse;
import com.errday.kafkahelper.application.dto.KafkaTopicRequest;
import com.errday.kafkahelper.application.port.out.topic.KafkaTopicDescribePort;
import com.errday.kafkahelper.domain.KafkaTopic;
import com.errday.kafkahelper.domain.KafkaTopicConfig;
import com.errday.kafkahelper.domain.KafkaTopicDescribe;
import com.errday.kafkahelper.domain.KafkaTopicPartitionDescribe;
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
class KafkaTopicDescribeServiceTest {

    @Mock
    private KafkaTopicDescribePort kafkaTopicDescribePort;

    @InjectMocks
    private KafkaTopicDescribeService kafkaTopicDescribeService;

    @Test
    void describe() {

        KafkaTopicRequest request = new KafkaTopicRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                1,
                (short) 0,
                new KafkaTopicConfig()
        );

        KafkaTopicPartitionDescribe partitionDescribe0 = KafkaTopicPartitionDescribe.of(1, "localhost:9092 (id: 1 rack: null)", "[localhost:9092 (id: 1 rack: null)]", "[localhost:9092 (id: 1 rack: null)]");
        KafkaTopicPartitionDescribe partitionDescribe1 = KafkaTopicPartitionDescribe.of(2, "localhost:9092 (id: 1 rack: null)", "[localhost:9092 (id: 1 rack: null)]", "[localhost:9092 (id: 1 rack: null)]");
        KafkaTopicPartitionDescribe partitionDescribe2 = KafkaTopicPartitionDescribe.of(3, "localhost:9092 (id: 1 rack: null)", "[localhost:9092 (id: 1 rack: null)]", "[localhost:9092 (id: 1 rack: null)]");


        KafkaTopicDescribe mockTopicDescribe = KafkaTopicDescribe.of(
                "mockTopic",
                false,
                List.of(partitionDescribe0, partitionDescribe1, partitionDescribe2)
        );

        when(kafkaTopicDescribePort.describe(any(KafkaTopic.class)))
                .thenReturn(mockTopicDescribe);

        KafkaTopicDescribeResponse response = kafkaTopicDescribeService.describe(request);

        assertThat(response.topicName()).isEqualTo(mockTopicDescribe.getTopicName());
        assertThat(response.isInternal()).isEqualTo(mockTopicDescribe.isInternal());
        assertThat(response.partitions()).hasSize(mockTopicDescribe.partitionCount());
    }

}