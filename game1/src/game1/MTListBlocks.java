package game1;

public class MTListBlocks implements ListBlocks{
    
    public MTListBlocks() {}
    
    public int length() {
        return 0;
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public ListBlocks add(Blocks blocks) {
        return new ConsListBlocks(blocks, this);
    }
    
}
