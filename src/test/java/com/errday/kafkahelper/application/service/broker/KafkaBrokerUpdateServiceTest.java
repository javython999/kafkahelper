package com.errday.kafkahelper.application.service.broker;

import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.dto.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerFindPort;
import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerUpdatePort;
import com.errday.kafkahelper.domain.KafkaBroker;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class KafkaBrokerUpdateServiceTest {

    private static final long BROKER_ID = 1L;
    private static final String OLD_ALIAS = "old-alias";
    private static final String NEW_ALIAS = "new-alias";
    private static final String OLD_HOST = "old-host";
    private static final String NEW_HOST = "new-host";
    private static final int OLD_PORT = 9000;
    private static final int NEW_PORT = 9092;

    @Mock
    private KafkaBrokerUpdatePort kafkaBrokerUpdatePort;

    @Mock
    private KafkaBrokerFindPort kafkaBrokerFindPort;

    @InjectMocks
    private KafkaBrokerUpdateService kafkaBrokerUpdateService;

    @BeforeEach
    void setUp() {
        KafkaBroker mockOldBroker = KafkaBroker.of(BROKER_ID, OLD_ALIAS, OLD_HOST, OLD_PORT);
        when(kafkaBrokerFindPort.findById(any(long.class))).thenReturn(Optional.of(mockOldBroker));

        KafkaBroker mockBroker = KafkaBroker.of(BROKER_ID, NEW_ALIAS, NEW_HOST, NEW_PORT);
        when(kafkaBrokerUpdatePort.update(any(KafkaBroker.class))).thenReturn(mockBroker);
    }

    @Test
    void update() {
        KafkaBrokerUpdateRequest request =
                new KafkaBrokerUpdateRequest(
                        BROKER_ID,
                        NEW_ALIAS,
                        NEW_HOST,
                        NEW_PORT,
                        OLD_ALIAS,
                        OLD_HOST,
                        OLD_PORT
                );

        KafkaBrokerResponse response = kafkaBrokerUpdateService.update(request);

        assertThat(response.id()).isEqualTo(BROKER_ID);
        assertThat(response.alias()).isEqualTo(NEW_ALIAS);
        assertThat(response.host()).isEqualTo(NEW_HOST);
        assertThat(response.port()).isEqualTo(NEW_PORT);
    }
}