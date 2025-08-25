package com.errday.kafkahelper.application.service.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;
import com.errday.kafkahelper.application.error.KafkaBrokerNotFoundException;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerFindPort;
import com.errday.kafkahelper.application.service.kafka.broker.KafkaBrokerFindService;
import com.errday.kafkahelper.domain.kafka.KafkaBroker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class KafkaBrokerFindServiceTest {

    @Mock
    private KafkaBrokerFindPort kafkaBrokerFindPort;

    @InjectMocks
    private KafkaBrokerFindService findService;

    @Test
    void findById() {
        KafkaBroker broker = KafkaBroker.of(1L, "mock-broker", "mock-kafka-server.com", 9092);
        when(kafkaBrokerFindPort.findById(1L)).thenReturn(Optional.of(broker));

        KafkaBrokerResponse findBroker = findService.findById(1L);

        assertThat(findBroker.id()).isEqualTo(1L);
        assertThat(findBroker.alias()).isEqualTo("mock-broker");
        assertThat(findBroker.host()).isEqualTo("mock-kafka-server.com");
        assertThat(findBroker.port()).isEqualTo(9092);;
    }

    @Test
    void findByIdFail() {
        when(kafkaBrokerFindPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findService.findById(1L))
            .isInstanceOf(KafkaBrokerNotFoundException.class);
    }

}