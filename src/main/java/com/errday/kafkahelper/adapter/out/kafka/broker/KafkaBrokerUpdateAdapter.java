package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerUpdatePort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaBrokerUpdateAdapter implements KafkaBrokerUpdatePort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public KafkaBroker update(KafkaBroker kafkaBroker) {
        kafkaBrokerRepository.save(KafkaBrokerEntity.from(kafkaBroker));
        return kafkaBroker;
    }
}
