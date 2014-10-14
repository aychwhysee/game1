package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public interface ListBlocks {
    
    public WorldImage drawListBlocks();

    public int length();

    public boolean isEmpty();

    public ListBlocks add(Blocks blocks);

    public boolean getHit(PlayerBlock playerblock);
    
    public ListBlocks fall();
}
