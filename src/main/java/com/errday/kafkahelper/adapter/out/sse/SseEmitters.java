package com.errday.kafkahelper.adapter.out.sse;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class SseEmitters {

    private final Map<String, List<SseEmitter>> topicEmitters = new ConcurrentHashMap<>();
    private final ConsumerCleanupManager cleanupManager;
    private final Map<String, Runnable> cleanupCallbacks = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * cleanup 동작을 외부에서 주입
     */
    public void registerCleanupCallback(String topic, Runnable callback) {
        cleanupCallbacks.put(topic, callback);
    }

    public SseEmitter createEmitter(String topic) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        topicEmitters.computeIfAbsent(topic, k -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removeEmitter(topic, emitter));
        emitter.onTimeout(() -> removeEmitter(topic, emitter));
        emitter.onError(e -> {
            log.debug("SSE 연결 에러: {}", e.getMessage());
            removeEmitter(topic, emitter);
        });

        // 새 연결 → 종료 타이머 취소
        cleanupManager.cancelCleanup(topic);
        return emitter;
    }

    private void removeEmitter(String topic, SseEmitter emitter) {
        List<SseEmitter> emitters = topicEmitters.get(topic);
        if (emitters != null) {
            emitters.remove(emitter);
            if (emitters.isEmpty()) {
                Runnable callback = cleanupCallbacks.get(topic);
                if (callback != null) {
                    cleanupManager.scheduleCleanup(topic, callback);
                }
            }
        }
    }

    public void sendToTopic(String topic, ConsumerRecord<String, String> record) {
        List<SseEmitter> emitters = topicEmitters.getOrDefault(topic, List.of());
        List<SseEmitter> deadEmitters = new ArrayList<>();

        for (SseEmitter emitter : emitters) {
            try {

                HashMap<String, String> recordInfo = new HashMap<>();
                LocalDateTime dateTime = Instant.ofEpochMilli(record.timestamp())
                        .atZone(ZoneId.of("Asia/Seoul"))
                        .toLocalDateTime();

                recordInfo.put("key", record.key() == null ? "" : record.key());
                recordInfo.put("message", record.value() == null ? "" : record.value());
                recordInfo.put("timestamp", dateTime.toString());

                emitter.send(SseEmitter.event().name("message").data(objectMapper.writeValueAsString(recordInfo)));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        }

        emitters.removeAll(deadEmitters);
    }

    public boolean hasEmitters(String topic) {
        return !topicEmitters.getOrDefault(topic, List.of()).isEmpty();
    }
}