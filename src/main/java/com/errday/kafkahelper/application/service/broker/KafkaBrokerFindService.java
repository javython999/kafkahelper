package com.errday.kafkahelper.application.service.broker;

import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.error.KafkaBrokerNotFoundException;
import com.errday.kafkahelper.application.port.in.broker.KafkaBrokerFindUseCase;
import com.errday.kafkahelper.application.port.out.broker.KafkaBrokerFindPort;
import com.errday.kafkahelper.domain.KafkaBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaBrokerFindService implements KafkaBrokerFindUseCase {

    private final KafkaBrokerFindPort kafkaBrokerFindPort;

    @Override
    public KafkaBrokerResponse findById(long id) {

        KafkaBroker kafkaBroker = kafkaBrokerFindPort.findById(id)
                .orElseThrow(() -> new KafkaBrokerNotFoundException(id));

        return KafkaBrokerResponse.from(kafkaBroker);
    }
}
