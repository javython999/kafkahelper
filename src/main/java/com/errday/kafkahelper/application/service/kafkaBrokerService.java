package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.dto.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.dto.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.port.in.KafkaBrokerDeleteUseCase;
import com.errday.kafkahelper.application.port.in.KafkaBrokerRegisterUseCase;
import com.errday.kafkahelper.application.port.in.KafkaBrokerUpdateUseCase;
import com.errday.kafkahelper.application.port.out.KafkaBrokerDeletePort;
import com.errday.kafkahelper.application.port.out.KafkaBrokerFindPort;
import com.errday.kafkahelper.application.port.out.KafkaBrokerRegisterPort;
import com.errday.kafkahelper.application.port.out.KafkaBrokerUpdatePort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class kafkaBrokerService implements KafkaBrokerRegisterUseCase, KafkaBrokerUpdateUseCase, KafkaBrokerDeleteUseCase {

    private final KafkaBrokerRegisterPort kafkaBrokerRegisterPort;
    private final KafkaBrokerUpdatePort kafkaBrokerUpdatePort;
    private final KafkaBrokerFindPort kafkaBrokerFindPort;
    private final KafkaBrokerDeletePort kafkaBrokerDeletePort;

    @Override
    public KafkaBrokerResponse register(KafkaBrokerRegisterRequest request) {
        KafkaBroker kafkaBroker = KafkaBroker.of(null, request.alias(), request.host(), request.port());
        KafkaBroker saved = kafkaBrokerRegisterPort.save(kafkaBroker);
        return KafkaBrokerResponse.of(saved.getId(),saved.getAlias(),saved.getHost(),saved.getPort());
    }

    @Override
    public KafkaBrokerResponse update(KafkaBrokerUpdateRequest request) {
        KafkaBroker kafkaBroker = kafkaBrokerFindPort.findById(request.id());
        kafkaBroker.update(request.alias(), request.host(), request.port());
        return KafkaBrokerResponse.from(kafkaBrokerUpdatePort.update(kafkaBroker));
    }

    @Override
    public boolean deleteById(long id) {
        return kafkaBrokerDeletePort.delete(id);
    }
}
