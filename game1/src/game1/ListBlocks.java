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

    public boolean getHit(PlayerBlock playerblock);

    public ListBlocks fall();
}
