package com.errday.kafkahelper.application.service.broker;

import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.dto.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.error.KafkaBrokerNotFoundException;
import com.errday.kafkahelper.application.port.in.broker.KafkaBrokerUpdateUseCase;
import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerFindPort;
import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerUpdatePort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaBrokerUpdateService implements KafkaBrokerUpdateUseCase {

    private final KafkaBrokerUpdatePort kafkaBrokerUpdatePort;
    private final KafkaBrokerFindPort kafkaBrokerFindPort;

    @Override
    public KafkaBrokerResponse update(KafkaBrokerUpdateRequest request) {
        KafkaBroker kafkaBroker = kafkaBrokerFindPort.findById(request.id())
                .orElseThrow(() -> new KafkaBrokerNotFoundException(request.id()));
        kafkaBroker.update(request.alias(), request.host(), request.port());
        return KafkaBrokerResponse.from(kafkaBrokerUpdatePort.update(kafkaBroker));
    }
}
