package com.errday.kafkahelper.domain.model;

import java.lang.reflect.Field;

public class KafkaBrokerFixture {

    public static KafkaBroker kafkaBroker(Long id, String alias, String host, int port) {
        KafkaBrokerRegisterRequest request = new KafkaBrokerRegisterRequest(alias, new BootstrapServer(host, port));

        KafkaBroker kafkaBroker = KafkaBroker.of(request);

        try {
            Field idField = KafkaBroker.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(kafkaBroker, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return kafkaBroker;
    }
}
