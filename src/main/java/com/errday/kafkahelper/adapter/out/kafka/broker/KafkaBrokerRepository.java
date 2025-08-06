package com.errday.kafkahelper.adapter.out.kafka.broker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaBrokerRepository extends JpaRepository<KafkaBrokerEntity, Long> {
}
