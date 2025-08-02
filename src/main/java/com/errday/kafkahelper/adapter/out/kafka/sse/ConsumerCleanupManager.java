package com.errday.kafkahelper.adapter.out.kafka.sse;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

@Component
public class ConsumerCleanupManager {

    private final Map<String, ScheduledFuture<?>> cleanupTasks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void scheduleCleanup(String topic, Runnable cleanupAction) {
        if (cleanupTasks.containsKey(topic)) return;

        ScheduledFuture<?> future = scheduler.schedule(() -> {
            cleanupAction.run();
            cleanupTasks.remove(topic);
        }, 60, TimeUnit.SECONDS);

        cleanupTasks.put(topic, future);
    }

    public void cancelCleanup(String topic) {
        ScheduledFuture<?> future = cleanupTasks.remove(topic);
        if (future != null) future.cancel(false);
    }

    @PreDestroy
    public void destroy() {
        scheduler.shutdown();
    }
}