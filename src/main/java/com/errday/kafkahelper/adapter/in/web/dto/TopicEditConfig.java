package com.errday.kafkahelper.adapter.in.web.dto;

public record TopicEditConfig(
        Long retentionMs,
        Long retentionBytes,
        String cleanupPolicy,
        Long maxMessageBytes,
        Long segmentBytes,
        Long segmentMs,
        Float minCleanableDirtyRatio,
        Long deleteRetentionMs,
        Long flushMessages,
        Long flushMs,
        String messageTimestampType,
        String compressionType
) {
}
