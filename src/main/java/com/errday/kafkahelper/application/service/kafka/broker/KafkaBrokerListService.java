package com.errday.kafkahelper.application.service.kafka.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.in.kafka.broker.KafkaBrokerListUseCase;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaBrokerListService implements KafkaBrokerListUseCase {

    private final KafkaBrokerListPort kafkaBrokerListPort;

    @Override
    public List<KafkaBrokerResponse> findAll() {
        return kafkaBrokerListPort.findAll()
                .stream()
                .map(KafkaBrokerResponse::from)
                .toList();
    }

}
