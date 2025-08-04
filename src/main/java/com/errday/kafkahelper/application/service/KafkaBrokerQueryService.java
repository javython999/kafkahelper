package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.dto.KafkaBrokerResponse;
import com.errday.kafkahelper.application.port.in.KafkaBrokerListUseCase;
import com.errday.kafkahelper.application.port.out.KafkaBrokerQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaBrokerQueryService implements KafkaBrokerListUseCase {

    private final KafkaBrokerQueryPort kafkaBrokerQueryPort;

    @Override
    public List<KafkaBrokerResponse> findAll() {
        return kafkaBrokerQueryPort.findAll()
                .stream()
                .map(KafkaBrokerResponse::from)
                .toList();
    }

    @Override
    public KafkaBrokerResponse findById(long id) {
        return KafkaBrokerResponse.from(kafkaBrokerQueryPort.findById(id));
    }
}
