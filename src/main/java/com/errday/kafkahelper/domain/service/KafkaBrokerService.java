package com.errday.kafkahelper.domain.service;

import com.errday.kafkahelper.domain.model.KafkaBroker;
import com.errday.kafkahelper.domain.model.KafkaBrokerRegisterRequest;
import com.errday.kafkahelper.domain.model.KafkaBrokerUpdateRequest;
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

    public KafkaBroker update(KafkaBrokerUpdateRequest request) {
        KafkaBroker broker = findById(request.id());
        broker.updateAlias(request.alias());
        broker.updateHost(request.host());
        broker.updatePort(request.port());
        return kafkaBrokerRepository.save(broker);
    }

    public KafkaBroker findById(long id) {
        return kafkaBrokerRepository.findById(id).orElseThrow();
    }

    public List<KafkaBroker> findAll() {
        return kafkaBrokerRepository.findAll();
    }

    public boolean deleteById(long id) {
        if (kafkaBrokerRepository.existsById(id)) {
            kafkaBrokerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
