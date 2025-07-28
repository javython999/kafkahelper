package com.errday.kafkahelper.domain.service;

import com.errday.kafkahelper.domain.model.KafkaBroker;
import com.errday.kafkahelper.domain.model.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.domain.repository.KafkaBrokerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaBrokerService {

    private final KafkaBrokerRepository kafkaBrokerRepository;

    public KafkaBroker save(KafkaBrokerRegisterRequest request) {
        return kafkaBrokerRepository.save(KafkaBroker.of(request));
    }

    public List<KafkaBroker> findAll() {
        return kafkaBrokerRepository.findAll();
    }
}
