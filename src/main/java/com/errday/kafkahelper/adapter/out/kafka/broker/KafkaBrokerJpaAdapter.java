package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.application.port.out.KafkaBrokerPersistencePort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaBrokerJpaAdapter implements KafkaBrokerPersistencePort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public KafkaBroker save(KafkaBroker kafkaBroker) {

        KafkaBrokerEntity kafkaBrokerEntity = KafkaBrokerEntity.from(kafkaBroker);

        KafkaBrokerEntity saved = kafkaBrokerRepository.save(kafkaBrokerEntity);

        return saved.toDomain();
    }

    @Override
    public KafkaBroker update(KafkaBroker kafkaBroker) {
        kafkaBrokerRepository.save(KafkaBrokerEntity.from(kafkaBroker));
        return kafkaBroker;
    }

    @Override
    public boolean delete(long id) {
        if (kafkaBrokerRepository.existsById(id)) {
            kafkaBrokerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
