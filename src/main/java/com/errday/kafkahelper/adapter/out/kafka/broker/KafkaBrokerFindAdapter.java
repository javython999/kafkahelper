package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.adapter.out.kafka.broker.entity.KafkaBrokerEntity;
import com.errday.kafkahelper.adapter.out.kafka.broker.entity.KafkaBrokerRepository;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerFindPort;
import com.errday.kafkahelper.domain.kafka.KafkaBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaBrokerFindAdapter implements KafkaBrokerFindPort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public Optional<KafkaBroker> findById(long id) {
        return kafkaBrokerRepository.findById(id)
                .map(KafkaBrokerEntity::toDomain);
    }
}
