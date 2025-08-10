package com.errday.kafkahelper.application.error;

public class KafkaBrokerNotFoundException extends RuntimeException {

    public KafkaBrokerNotFoundException(long brokerId) {
        super("Kafka broker not found with id: " + brokerId);
    }
}
