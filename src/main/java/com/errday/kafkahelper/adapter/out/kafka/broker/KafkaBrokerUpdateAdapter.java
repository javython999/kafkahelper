package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.adapter.out.kafka.broker.entity.KafkaBrokerEntity;
import com.errday.kafkahelper.adapter.out.kafka.broker.entity.KafkaBrokerRepository;
import com.errday.kafkahelper.application.error.KafkaBrokerNotFoundException;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerUpdatePort;
import com.errday.kafkahelper.domain.kafka.KafkaBroker;
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

        KafkaBrokerEntity kafkaBrokerEntity = kafkaBrokerRepository.findById(kafkaBroker.getId())
                .orElseThrow(() -> new KafkaBrokerNotFoundException(kafkaBroker.getId()));

        kafkaBrokerEntity.updateAlias(kafkaBroker.getAlias());
        kafkaBrokerEntity.updateHost(kafkaBroker.getHost());
        kafkaBrokerEntity.updatePort(kafkaBroker.getPort());

        kafkaBrokerRepository.save(kafkaBrokerEntity);
        return kafkaBroker;
    }
}
