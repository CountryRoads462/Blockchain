package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockChain {

    private static int numberOfZeros = 0;

    private static List<String> messages = new ArrayList<>();

    private final List<Block> blockChain = new ArrayList<>();

    public void generateNewBlock() {
        int id = blockChain.size() + 1;

        String prevHash;
        if (id == 1) {
            prevHash = "0";
        } else {
            prevHash = blockChain.get(blockChain.size() - 1).getHash();
        }

        Random random = new Random();
        String message = "";
        int oldNumber = numberOfZeros;
        boolean flag = true;
        while (flag) {
            int randomNumber = random.nextInt(3);
            switch (randomNumber) {
                case 1: {
                    numberOfZeros = numberOfZeros + 1;
                    flag = false;
                    message = String.format("N was increased to %d", numberOfZeros);
                    break;
                }
                case 2: {
                    if (numberOfZeros - 1 >= 0) {
                        numberOfZeros = numberOfZeros - 1;
                        flag = false;
                        message = "N was decreased by 1";
                    }
                    break;
                }
                default: {
                    flag = false;
                    message = "N stays the same";
                    break;
                }
            }
        }

        ResultOfMining resultOfMining = Miners.mine(oldNumber);

        assert resultOfMining != null;
        String nameOfMiner = resultOfMining.getNameOfMiner();
        long magicNumber = resultOfMining.getMagicNumber();
        String hash = resultOfMining.getHash();
        int generationTime = resultOfMining.getGenerationTime();

        Block newBlock = new Block(nameOfMiner, id, magicNumber, prevHash, hash, messages, generationTime, message);
        messages.clear();
        blockChain.add(newBlock);

        System.out.println(newBlock + "\n");
    }

    public static void receiveMessage(String msg) {
        messages.add(msg);
    }

    public int getSize() {
        return blockChain.size();
    }
}
