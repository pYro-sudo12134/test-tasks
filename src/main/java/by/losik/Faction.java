package by.losik;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

class Faction implements Runnable {
    private final String name;
    private final Map<PartType, AtomicInteger> inventory;
    private final AtomicInteger robotsBuilt = new AtomicInteger(0);
    private final Random random = new Random();
    private Factory currentFactory;
    private CountDownLatch currentNightStartLatch;
    private CountDownLatch currentNightEndLatch;
    private static final int MAX_PARTS_PER_NIGHT = 5;
    private static final int CARRY_BACK_DELAY = 30;

    public Faction(String name) {
        this.name = name;
        this.inventory = Arrays.stream(PartType.values())
                .collect(Collectors.toMap(type -> type, type -> new AtomicInteger(0)));
    }

    public void prepareForNight(Factory factory, CountDownLatch nightStartLatch, CountDownLatch nightEndLatch) {
        this.currentFactory = factory;
        this.currentNightStartLatch = nightStartLatch;
        this.currentNightEndLatch = nightEndLatch;
    }

    @Override
    public void run() {
        try {
            currentNightStartLatch.await();

            int partsTaken = 0;
            while (partsTaken < MAX_PARTS_PER_NIGHT && currentFactory.hasParts() && !Thread.currentThread().isInterrupted()) {
                PartType part = currentFactory.takePart(this);
                if (part != null) {
                    inventory.get(part).incrementAndGet();
                    partsTaken++;
                    Thread.sleep(random.nextInt(CARRY_BACK_DELAY)); //чисто для симуляции
                } else {
                    break;
                }
            }

            buildRobots();

            System.out.printf("%s took %d parts, Robots: %d, Inventory: %s%n",
                    name, partsTaken, robotsBuilt.get(), getInventoryString());

            currentNightEndLatch.countDown();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void buildRobots() {
        int heads = inventory.get(PartType.HEAD).get();
        int torsos = inventory.get(PartType.TORSO).get();
        int hands = inventory.get(PartType.HAND).get() / 2;
        int feet = inventory.get(PartType.FEET).get() / 2;

        int maxPossibleRobots = Math.min(Math.min(heads, torsos), Math.min(hands, feet));

        if (maxPossibleRobots > 0) {
            inventory.get(PartType.HEAD).addAndGet(-maxPossibleRobots);
            inventory.get(PartType.TORSO).addAndGet(-maxPossibleRobots);
            inventory.get(PartType.HAND).addAndGet(-maxPossibleRobots * 2);
            inventory.get(PartType.FEET).addAndGet(-maxPossibleRobots * 2);

            int totalBuilt = robotsBuilt.addAndGet(maxPossibleRobots);
            System.out.printf(">>> %s built %d robot(s)! Total: %d%n",
                    name, maxPossibleRobots, totalBuilt);
        }
    }

    public int getRobotsCount() {
        return robotsBuilt.get();
    }

    public String getName() {
        return name;
    }

    public String getInventoryString() {
        return Arrays.stream(PartType.values())
                .map(type -> String.format("%s: %d", type, inventory.get(type).get()))
                .collect(Collectors.joining(", "));
    }
}