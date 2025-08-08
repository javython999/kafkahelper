package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.in.KafkaBrokerFindUseCase;
import com.errday.kafkahelper.application.port.in.KafkaBrokerListUseCase;
import com.errday.kafkahelper.application.port.out.KafkaBrokerFindPort;
import com.errday.kafkahelper.application.port.out.KafkaBrokerListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaBrokerQueryService implements KafkaBrokerListUseCase, KafkaBrokerFindUseCase {

    private final KafkaBrokerFindPort kafkaBrokerFindPort;
    private final KafkaBrokerListPort kafkaBrokerListPort;

    @Override
    public List<KafkaBrokerResponse> findAll() {
        return kafkaBrokerListPort.findAll()
                .stream()
                .map(KafkaBrokerResponse::from)
                .toList();
    }

    @Override
    public KafkaBrokerResponse findById(long id) {
        return KafkaBrokerResponse.from(kafkaBrokerFindPort.findById(id));
    }
}
