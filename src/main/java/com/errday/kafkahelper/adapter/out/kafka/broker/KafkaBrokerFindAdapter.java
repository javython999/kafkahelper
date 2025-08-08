package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.application.port.out.KafkaBrokerFindPort;
import com.errday.kafkahelper.domain.KafkaBroker;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaBrokerFindAdapter implements KafkaBrokerFindPort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public KafkaBroker findById(long id) {
        return kafkaBrokerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kafka Broker with id " + id + " not found!"))
                .toDomain();
    }
}
