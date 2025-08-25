package com.errday.kafkahelper.application.service.kafka.broker;

import com.errday.kafkahelper.application.port.in.kafka.broker.KafkaBrokerDeleteUseCase;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerDeletePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaBrokerDeleteService implements KafkaBrokerDeleteUseCase {

    private final KafkaBrokerDeletePort kafkaBrokerDeletePort;

    @Override
    public boolean deleteById(long id) {
        return kafkaBrokerDeletePort.delete(id);
    }
}
