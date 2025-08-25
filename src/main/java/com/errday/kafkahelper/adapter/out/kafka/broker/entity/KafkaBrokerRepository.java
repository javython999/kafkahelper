package com.errday.kafkahelper.adapter.out.kafka.broker.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaBrokerRepository extends JpaRepository<KafkaBrokerEntity, Long> {
}
