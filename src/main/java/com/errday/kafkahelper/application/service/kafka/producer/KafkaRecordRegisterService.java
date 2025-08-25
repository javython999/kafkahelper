package com.errday.kafkahelper.application.service.kafka.producer;

import com.errday.kafkahelper.application.dto.kafka.KafkaRecordRegisterRequest;
import com.errday.kafkahelper.application.port.in.kafka.producer.KafkaRecordRegisterUseCase;
import com.errday.kafkahelper.application.port.out.kafka.producer.KafkaRecordRegisterPort;
import com.errday.kafkahelper.domain.kafka.KafkaBootStrapServer;
import com.errday.kafkahelper.domain.kafka.KafkaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaRecordRegisterService implements KafkaRecordRegisterUseCase {

    private final KafkaRecordRegisterPort  kafkaRecordRegisterPort;


    @Override
    public boolean register(KafkaRecordRegisterRequest request) {
        KafkaRecord kafkaRecord = KafkaRecord.of(
            KafkaBootStrapServer.of(request.bootstrapServer().host(), request.bootstrapServer().port()),
                request.topicName(),
                request.key(),
                request.message()
        );

        return kafkaRecordRegisterPort.save(kafkaRecord);
    }
}
