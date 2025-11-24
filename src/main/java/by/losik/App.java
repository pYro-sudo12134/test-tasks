package by.losik;

import java.util.concurrent.*;

public class App {
    private static final int MAX_DAYS = 100;
    public static void main(String[] args) throws InterruptedException {
        Factory factory = new Factory();

        Faction worldFaction = new Faction("World");
        Faction wednesdayFaction = new Faction("Wednesday");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            for (int day = 0; day < MAX_DAYS; day++) {
                System.out.println("\n=== Day " + (day + 1) + " ===");
                factory.produceDailyParts();
                CountDownLatch nightStartLatch = new CountDownLatch(1);
                CountDownLatch nightEndLatch = new CountDownLatch(2);
                wednesdayFaction.prepareForNight(factory, nightStartLatch, nightEndLatch);
                worldFaction.prepareForNight(factory, nightStartLatch, nightEndLatch);
                Future<?> wednesdayFuture = executor.submit(wednesdayFaction);
                Future<?> worldFuture = executor.submit(worldFaction);
                factory.startNight();
                nightStartLatch.countDown();
                nightEndLatch.await();
                factory.endNight();
                try {
                    worldFuture.get();
                    wednesdayFuture.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.printf("Daily summary - World: %d robots, Wednesday: %d robots%n",
                        worldFaction.getRobotsCount(), wednesdayFaction.getRobotsCount());
            }
        } finally {
            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        }

        printFinalResults(worldFaction, wednesdayFaction);
    }

    private static void printFinalResults(Faction worldFaction, Faction wednesdayFaction) {
        System.out.println("\n=== FINAL RESULTS ===");
        System.out.printf("World faction: %d robots%n", worldFaction.getRobotsCount());
        System.out.printf("Wednesday faction: %d robots%n", wednesdayFaction.getRobotsCount());

        if (worldFaction.getRobotsCount() > wednesdayFaction.getRobotsCount()) {
            System.out.println("World faction has the strongest army!");
        } else if (wednesdayFaction.getRobotsCount() > worldFaction.getRobotsCount()) {
            System.out.println("Wednesday faction has the strongest army!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}