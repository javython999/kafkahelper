package com.errday.kafkahelper.domain.kafka;

import lombok.Getter;

@Getter
public class KafkaTopicConfig {

    private String cleanupPolicy;
    private String compressionType;
    private Long deleteRetentionMs;
    private Long fileDeleteDelayMs;
    private Long flushMessages;
    private Long flushMs;
    private Integer indexIntervalBytes;
    private Long maxCompactionLagMs;
    private Long maxMessageBytes;
    private String messageTimestampType;
    private Double minCleanableDirtyRatio;
    private Long minCompactionLagMs;
    private Integer minInsyncReplicas;
    private Boolean preallocate;
    private Long retentionBytes;
    private Long retentionMs;
    private Integer segmentBytes;
    private Integer segmentIndexBytes;
    private Long segmentJitterMs;
    private Long segmentMs;
}
