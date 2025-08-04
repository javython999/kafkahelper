package com.errday.kafkahelper.adapter.out.kafka.persistence;

import com.errday.kafkahelper.application.port.out.KafkaBrokerQueryPort;
import com.errday.kafkahelper.domain.model.KafkaBroker;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaBrokerQueryJpaAdapter implements KafkaBrokerQueryPort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public List<KafkaBroker> findAll() {
        return kafkaBrokerRepository.findAll()
                .stream()
                .map(KafkaBrokerEntity::toDomain)
                .toList();
    }

    @Override
    public KafkaBroker findById(long id) {
        return kafkaBrokerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kafka Broker with id " + id + " not found!"))
                .toDomain();
    }
}
