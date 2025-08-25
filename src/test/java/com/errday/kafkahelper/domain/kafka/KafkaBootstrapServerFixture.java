package com.errday.kafkahelper.domain.kafka;

public class KafkaBootstrapServerFixture {

    public static KafkaBootStrapServer mock() {
        return KafkaBootStrapServer.of("localhost", 9092);
    }
}
