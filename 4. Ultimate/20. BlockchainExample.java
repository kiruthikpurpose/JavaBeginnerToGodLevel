import java.util.ArrayList;

public class BlockchainExample {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        blockchain.addBlock(new Block("First block", "0"));
        blockchain.addBlock(new Block("Second block", blockchain.getLatestBlock().getHash()));
        blockchain.addBlock(new Block("Third block", blockchain.getLatestBlock().getHash()));

        for (Block block : blockchain.getChain()) {
            System.out.println(block);
        }
    }
}

class Block {
    private String data;
    private String previousHash;
    private String hash;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = data + previousHash;
        return Integer.toString(input.hashCode());
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Block{data='" + data + "', previousHash='" + previousHash + "', hash='" + hash + "'}";
    }
}

class Blockchain {
    private ArrayList<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
    }

    public void addBlock(Block block) {
        chain.add(block);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public ArrayList<Block> getChain() {
        return chain;
    }
}
