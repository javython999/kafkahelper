package com.errday.kafkahelper.application.service.broker;


import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerListPort;
import com.errday.kafkahelper.domain.KafkaBroker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaBrokerListServiceTest {

    @Mock
    private KafkaBrokerListPort kafkaBrokerListPort;

    @InjectMocks
    private KafkaBrokerListService kafkaBrokerListService;


    @Test
    void findAll() {
        KafkaBroker mockBroker1 = KafkaBroker.of(1L, "mock-broker-1", "mock-broker.com", 9092);
        KafkaBroker mockBroker2 = KafkaBroker.of(2L, "mock-broker-2", "mock-kafka.com", 9093);
        when(kafkaBrokerListPort.findAll()).thenReturn(List.of(mockBroker1, mockBroker2));

        List<KafkaBrokerResponse> findAll = kafkaBrokerListService.findAll();

        assertThat(findAll).hasSize(2);
        assertThat(findAll).containsExactly(KafkaBrokerResponse.from(mockBroker1), KafkaBrokerResponse.from(mockBroker2));
    }

    @Test
    void findAllEmpty() {
        when(kafkaBrokerListPort.findAll()).thenReturn(Collections.emptyList());

        List<KafkaBrokerResponse> findAll = kafkaBrokerListService.findAll();

        assertThat(findAll).isEmpty();
        assertThat(findAll).isEqualTo(Collections.emptyList());
    }
}