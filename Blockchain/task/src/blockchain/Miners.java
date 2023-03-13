package blockchain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Miners {

    public static ResultOfMining mine(int numberOfZeros) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Callable<ResultOfMining>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            callables.add(() -> {
                Random random = new Random();
                int randomMagicNumber = random.nextInt(1000000000);

                class Checker {

                    boolean isCorrectNumberOfZeros(int arg, String hash) {
                        if (hash.length() < arg) {
                            return false;
                        }
                        for (int i = 0; i < arg; i++) {
                            if (hash.charAt(i) != '0') {
                                return false;
                            }
                        }
                        return true;
                    }
                }
                Checker checker = new Checker();

                LocalTime startTime = LocalTime.now();
                String hash;
                while (true) {
                    hash = StringUtil.applySha256(String.valueOf(randomMagicNumber));
                    if (checker.isCorrectNumberOfZeros(numberOfZeros, hash)) {
                        break;
                    }

                    randomMagicNumber = random.nextInt(1000000000);
                }

                LocalTime endTime = LocalTime.now();

                String minerName = Thread.currentThread().getName().replaceAll("(minerpool|pool)-\\d+-thread-", "");

                return new ResultOfMining(minerName, hash, randomMagicNumber, endTime.toSecondOfDay() - startTime.toSecondOfDay());
            });
        }
        try {
            return executor.invokeAny(callables);
        } catch (InterruptedException | ExecutionException ignored) {}

        return null;
    }
}


class ResultOfMining {

    private final String nameOfMiner;
    private final String hash;
    private final long magicNumber;
    private final int generationTime;

    public ResultOfMining(String nameOfMiner, String hash, long magicNumber, int generationTime) {
        this.nameOfMiner = nameOfMiner;
        this.hash = hash;
        this.magicNumber = magicNumber;
        this.generationTime = generationTime;
    }

    public String getNameOfMiner() {
        return nameOfMiner;
    }

    public String getHash() {
        return hash;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public int getGenerationTime() {
        return generationTime;
    }
}

