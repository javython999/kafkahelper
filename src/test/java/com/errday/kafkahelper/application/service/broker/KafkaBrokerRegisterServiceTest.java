package com.errday.kafkahelper.application.service.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBootstrapServerRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerRegisterPort;
import com.errday.kafkahelper.application.service.kafka.broker.KafkaBrokerRegisterService;
import com.errday.kafkahelper.domain.kafka.KafkaBroker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaBrokerRegisterServiceTest {

    @Mock
    private KafkaBrokerRegisterPort kafkaBrokerRegisterPort;

    @InjectMocks
    private KafkaBrokerRegisterService kafkaBrokerRegisterService;


    @Test
    void register() {
        KafkaBroker broker = KafkaBroker.of(1L, "mock-broker", "mock-kafka-server.com", 9092);
        when(kafkaBrokerRegisterPort.save(any(KafkaBroker.class))).thenReturn(broker);

        KafkaBrokerRegisterRequest request = new KafkaBrokerRegisterRequest(
                "mock-broker",
                new KafkaBootstrapServerRequest("mock-kafka-server.com", 9092)
        );

        KafkaBrokerResponse response = kafkaBrokerRegisterService.register(request);

        assertThat(response.id()).isEqualTo(broker.getId());
        assertThat(response.alias()).isEqualTo(broker.getAlias());
        assertThat(response.host()).isEqualTo(broker.getHost());
        assertThat(response.port()).isEqualTo(broker.getPort());
    }

}