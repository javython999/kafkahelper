package com.errday.kafkahelper.application.service.kafka.broker;

import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerResponse;
import com.errday.kafkahelper.application.dto.kafka.KafkaBrokerUpdateRequest;
import com.errday.kafkahelper.application.error.KafkaBrokerNotFoundException;
import com.errday.kafkahelper.application.port.in.kafka.broker.KafkaBrokerUpdateUseCase;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerFindPort;
import com.errday.kafkahelper.application.port.out.kafka.broker.KafkaBrokerUpdatePort;
import com.errday.kafkahelper.domain.kafka.KafkaBroker;
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
