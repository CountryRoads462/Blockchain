package blockchain;

public class Main {

    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain();

        while (blockChain.getSize() != 15) {
            if (blockChain.getSize() == 1) {
                Sender.startSending();
            }
            blockChain.generateNewBlock();
        }

        Sender.stopSending();
    }
}
