package com.errday.kafkahelper.domain.repository;

import com.errday.kafkahelper.domain.model.KafkaBroker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaBrokerRepository extends JpaRepository<KafkaBroker, Long> {
}
