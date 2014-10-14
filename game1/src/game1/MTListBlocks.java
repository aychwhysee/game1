package game1;
import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class MTListBlocks implements ListBlocks{
    
    public MTListBlocks() {
        
    }
    
    public WorldImage drawListBlocks() {
        return new RectangleImage(new Posn(0, 0), 0, 0, new Black());
    }
    
    public int length() {
        return 0;
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public ListBlocks add(Blocks blocks) {
        return new ConsListBlocks(blocks, this);
    }
    
    public boolean getHit(PlayerBlock playerblock) {
        return false;
    }
    
    public ListBlocks fall() {
        return new MTListBlocks();
    }
    
}
