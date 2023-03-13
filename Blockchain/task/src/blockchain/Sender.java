package blockchain;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sender {

    private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public static void startSending() {
        executor.scheduleAtFixedRate(() -> {
               Random random = new Random();
               int randomMessage = random.nextInt(50);
               BlockChain.receiveMessage(String.valueOf(randomMessage));
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    public static void stopSending() {
        executor.shutdown();
    }
}
