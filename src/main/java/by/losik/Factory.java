package by.losik;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

class Factory {
    private final BlockingQueue<PartType> storage = new LinkedBlockingQueue<>();
    private final Random random = new Random();
    private final Semaphore accessSemaphore = new Semaphore(1, true);
    private volatile boolean isNight = false;
    private final Object nightLock = new Object();
    private static final int MAX_DAILY_PRODUCTION = 10;
    private static final int APPROACH_DELAY = 50;
    public void produceDailyParts() {
        storage.clear();
        int partsToProduce = random.nextInt(MAX_DAILY_PRODUCTION) + 1;

        System.out.printf("Factory producing %d parts...%n", partsToProduce);

        for (int i = 0; i < partsToProduce; i++) {
            PartType part = PartType.values()[random.nextInt(PartType.values().length)];
            try {
                storage.put(part);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.printf("Factory produced: %s%n", getPartsCountString());
    }

    public PartType takePart(Faction faction) {
        if (Thread.currentThread().isInterrupted()) {
            return null;
        }

        synchronized (nightLock) {
            if (!isNight || storage.isEmpty()) {
                return null;
            }
        }

        try {
            Thread.sleep(random.nextInt(APPROACH_DELAY));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }

        try {
            if (accessSemaphore.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                try {
                    synchronized (nightLock) {
                        if (!isNight || storage.isEmpty()) {
                            return null;
                        }
                    }

                    PartType part = storage.poll();
                    if (part != null) {
                        System.out.printf("  %s takes %s from factory%n", faction.getName(), part);
                    }
                    return part;
                } finally {
                    accessSemaphore.release();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public void startNight() {
        isNight = true;
        System.out.println("=== Night falls - factions can take parts ===");
    }

    public void endNight() {
        isNight = false;
        System.out.println("=== Day breaks ===");
    }

    public boolean hasParts() {
        return !storage.isEmpty();
    }

    private String getPartsCountString() {
        Map<PartType, Long> count = storage.stream()
                .collect(Collectors.groupingBy(part -> part, Collectors.counting()));

        return Arrays.stream(PartType.values())
                .map(type -> String.format("%s: %d", type, count.getOrDefault(type, 0L)))
                .collect(Collectors.joining(", "));
    }
}