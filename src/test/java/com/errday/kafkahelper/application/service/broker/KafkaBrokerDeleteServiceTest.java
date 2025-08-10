package com.errday.kafkahelper.application.service.broker;


import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerDeletePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaBrokerDeleteServiceTest {

    @Mock
    private KafkaBrokerDeletePort kafkaBrokerDeletePort;

    @InjectMocks
    private KafkaBrokerDeleteService kafkaBrokerDeleteService;


    @Test
    void deleteById() {
        when(kafkaBrokerDeletePort.delete(1L)).thenReturn(true);

        boolean deleteResult = kafkaBrokerDeleteService.deleteById(1L);

        assertThat(deleteResult).isTrue();
    }

    @Test
    void deleteByIdFail() {
        when(kafkaBrokerDeletePort.delete(1L)).thenReturn(false);

        boolean deleteResult = kafkaBrokerDeleteService.deleteById(1L);

        assertThat(deleteResult).isFalse();
    }
}