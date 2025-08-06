package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.adapter.in.web.dto.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.in.KafkaBrokerDeleteUseCase;
import com.errday.kafkahelper.application.port.in.KafkaBrokerRegisterUseCase;
import com.errday.kafkahelper.application.port.in.KafkaBrokerUpdateUseCase;
import com.errday.kafkahelper.application.port.out.KafkaBrokerPersistencePort;
import com.errday.kafkahelper.application.port.out.KafkaBrokerQueryPort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class kafkaBrokerService implements KafkaBrokerRegisterUseCase, KafkaBrokerUpdateUseCase, KafkaBrokerDeleteUseCase {

    private final KafkaBrokerPersistencePort kafkaBrokerPersistencePort;
    private final KafkaBrokerQueryPort kafkaBrokerQueryPort;

    @Override
    public KafkaBrokerResponse register(KafkaBrokerRegisterRequest request) {
        KafkaBroker kafkaBroker = KafkaBroker.of(null, request.alias(), request.host(), request.port());
        KafkaBroker saved = kafkaBrokerPersistencePort.save(kafkaBroker);
        return KafkaBrokerResponse.of(saved.getId(),saved.getAlias(),saved.getHost(),saved.getPort());
    }

    @Override
    public KafkaBrokerResponse update(KafkaBrokerUpdateRequest request) {
        KafkaBroker kafkaBroker = kafkaBrokerQueryPort.findById(request.id());
        kafkaBroker.update(request.alias(), request.host(), request.port());
        return KafkaBrokerResponse.from(kafkaBrokerPersistencePort.update(kafkaBroker));
    }

    @Override
    public boolean deleteById(long id) {
        return kafkaBrokerPersistencePort.delete(id);
    }
}
