package com.errday.kafkahelper.adapter.in.web.dto;

public record TopicCreateRequestConfig(
        String cleanupPolicy,
        String compressionType,
        Long deleteRetentionMs,
        Long fileDeleteDelayMs,
        Integer flushMessages,
        Integer flushMs,
        Integer indexIntervalBytes,
        Long maxCompactionLagMs,
        Long maxMessageBytes,
        String messageTimestampType,
        Double minCleanableDirtyRatio,
        Long minCompactionLagMs,
        Integer minInsyncReplicas,
        Boolean preallocate,
        Long retentionBytes,
        Long retentionMs,
        Integer segmentBytes,
        Integer segmentIndexBytes,
        Long segmentJitterMs,
        Long segmentMs) {
}
