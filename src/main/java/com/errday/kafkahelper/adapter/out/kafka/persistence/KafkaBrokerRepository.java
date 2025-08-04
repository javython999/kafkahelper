package com.errday.kafkahelper.adapter.out.kafka.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaBrokerRepository extends JpaRepository<KafkaBrokerEntity, Long> {
}
