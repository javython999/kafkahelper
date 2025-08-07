package com.errday.kafkahelper.application.service;

import com.errday.kafkahelper.application.dto.KafkaRecordRegisterRequest;
import com.errday.kafkahelper.application.port.in.KafkaRecordRegisterUseCase;
import com.errday.kafkahelper.application.port.out.KafkaProducerCommandPort;
import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.KafkaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService implements KafkaRecordRegisterUseCase {

    private final KafkaProducerCommandPort kafkaProducerCommandPort;


    @Override
    public boolean register(KafkaRecordRegisterRequest request) {
        KafkaRecord kafkaRecord = KafkaRecord.of(
            KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.key(),
                request.message()
        );

        return kafkaProducerCommandPort.save(kafkaRecord);
    }
}
