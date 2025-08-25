package com.errday.kafkahelper.application.service.producer;


import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaRecordRegisterRequest;
import com.errday.kafkahelper.application.port.out.kafka.producer.KafkaRecordRegisterPort;
import com.errday.kafkahelper.application.service.kafka.producer.KafkaRecordRegisterService;
import com.errday.kafkahelper.domain.kafka.KafkaRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaRecordRegisterPort kafkaBrokerRegisterPort;

    @InjectMocks
    private KafkaRecordRegisterService kafkaRecordRegisterService;

    @Test
    void register() {

        when(kafkaBrokerRegisterPort.save(any(KafkaRecord.class)))
                .thenReturn(true);

        KafkaRecordRegisterRequest request = new KafkaRecordRegisterRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                "key",
                "value"
        );

        assertThat(kafkaRecordRegisterService.register(request))
                .isTrue();
    }

    @Test
    void registerFail() {
        when(kafkaBrokerRegisterPort.save(any(KafkaRecord.class)))
                .thenReturn(false);

        KafkaRecordRegisterRequest request = new KafkaRecordRegisterRequest(
                new KafkaBootstrapServerRequest("localhost", 9092),
                "mockTopic",
                "key",
                "value"
        );

        assertThat(kafkaRecordRegisterService.register(request))
                .isFalse();
    }
}