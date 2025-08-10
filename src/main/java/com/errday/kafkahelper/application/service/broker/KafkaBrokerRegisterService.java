package com.errday.kafkahelper.application.service.broker;

import com.errday.kafkahelper.application.dto.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.in.broker.KafkaBrokerRegisterUseCase;
import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerRegisterPort;
import com.errday.kafkahelper.domain.KafkaBroker;
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
