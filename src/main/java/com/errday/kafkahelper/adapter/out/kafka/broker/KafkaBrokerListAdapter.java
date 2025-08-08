package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.application.port.out.KafkaBrokerListPort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaBrokerListAdapter implements KafkaBrokerListPort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public List<KafkaBroker> findAll() {
        return kafkaBrokerRepository.findAll()
                .stream()
                .map(KafkaBrokerEntity::toDomain)
                .toList();
    }
}
