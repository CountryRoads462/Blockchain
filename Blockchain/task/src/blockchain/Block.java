package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {

    private final String nameOfMiner;
    private final int id;
    private final long timeStamp;
    private final long magicNumber;
    private final String prevHash;
    private final String hash;
    private final List<String> data;
    private final int generationTime;
    private final String messageAboutNumber;

    public Block(String nameOfMiner, int id, long magicNumber, String prevHash, String hash, List<String> data, int generationTime, String messageAboutNumber) {
        this.nameOfMiner = nameOfMiner;
        this.id = id;
        this.timeStamp = new Date().getTime();
        this.magicNumber = magicNumber;
        this.prevHash = prevHash;
        this.hash = hash;
        this.data = new ArrayList<>(data);
        this.generationTime = generationTime;
        this.messageAboutNumber = messageAboutNumber;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        String str = String.format("Block:\n" +
                "Created by: miner%s\n" +
                "miner%s gets 100 VC\n" +
                "Id: %d\n" +
                "Timestamp: %d\n" +
                "Magic number: %d\n" +
                "Hash of the previous block: \n" +
                "%s\n" +
                "Hash of the block: \n" +
                "%s\n",
                nameOfMiner,
                nameOfMiner,
                id,
                timeStamp,
                magicNumber,
                prevHash,
                hash);

        if (data.isEmpty()) {
            str += "Block data: no messages\n";
        } else {
            str += "Block data:\n";
            for (String message :
                    data) {
                str += message + "\n";
            }
        }

        str += String.format("Block was generating for %d seconds\n" +
                        "%s",
                generationTime,
                messageAboutNumber);

        return str;
    }
}
