package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerRegisterPort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaBrokerRegisterAdapter implements KafkaBrokerRegisterPort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public KafkaBroker save(KafkaBroker kafkaBroker) {
        KafkaBrokerEntity kafkaBrokerEntity = KafkaBrokerEntity.from(kafkaBroker);

        KafkaBrokerEntity saved = kafkaBrokerRepository.save(kafkaBrokerEntity);

        return saved.toDomain();
    }
}
