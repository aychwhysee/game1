package game1;

public class ConsListBlocks implements ListBlocks{
    
    public Blocks first;
    public ListBlocks rest;
    
    public ConsListBlocks(Blocks first, ListBlocks rest) {
        this.first = first;
        this.rest = rest;
    }
    
    public int length() {
        return rest.length() + 1;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public ListBlocks add(Blocks blocks) {
        return new ConsListBlocks(first, rest.add(blocks));
    }
    
}
