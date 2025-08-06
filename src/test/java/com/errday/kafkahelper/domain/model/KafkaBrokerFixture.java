package com.errday.kafkahelper.domain.model;

import com.errday.kafkahelper.adapter.out.kafka.broker.KafkaBrokerEntity;
import com.errday.kafkahelper.domain.KafkaBroker;

import java.lang.reflect.Field;

public class KafkaBrokerFixture {

    public static KafkaBrokerEntity kafkaBrokerEntity(Long id, String alias, String host, int port) {
        KafkaBroker kafkaBroker = KafkaBroker.of(id, alias, host, port);


        KafkaBrokerEntity kafkaBrokerEntity = KafkaBrokerEntity.from(kafkaBroker);

        try {
            Field idField = KafkaBroker.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(kafkaBroker, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return kafkaBrokerEntity;
    }
}
