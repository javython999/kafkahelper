package com.errday.kafkahelper.application.service.kafka.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.in.kafka.broker.KafkaBrokerRegisterUseCase;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerRegisterPort;
import com.errday.kafkahelper.domain.kafka.KafkaBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaBrokerRegisterService implements KafkaBrokerRegisterUseCase {

    private final KafkaBrokerRegisterPort kafkaBrokerRegisterPort;

    @Override
    public KafkaBrokerResponse register(KafkaBrokerRegisterRequest request) {
        KafkaBroker kafkaBroker = KafkaBroker.of(null, request.alias(), request.host(), request.port());
        KafkaBroker saved = kafkaBrokerRegisterPort.save(kafkaBroker);
        return KafkaBrokerResponse.of(saved.getId(),saved.getAlias(),saved.getHost(),saved.getPort());
    }
}
