package com.errday.kafkahelper.domain.model;

public record TopicAlterRequest(
        Long retentionMs,
        Long retentionBytes,
        String cleanupPolicy,
        Long maxMessageBytes,
        Long segmentBytes,
        Long segmentMs,
        Float minCleanableDirtyRatio,
        Long deleteRetentionMs,
        Integer flushMessages,
        Integer flushMs,
        String messageTimestampType,
        String compressionType) {
}
