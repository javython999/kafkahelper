package com.errday.kafkahelper.adapter.out.kafka.broker;

import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerDeletePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaBrokerDeleteAdapter implements KafkaBrokerDeletePort {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    @Override
    public boolean delete(long id) {
        if (kafkaBrokerRepository.existsById(id)) {
            kafkaBrokerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
