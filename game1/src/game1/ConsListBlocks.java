package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class ConsListBlocks implements ListBlocks {

    public Blocks first;
    public ListBlocks rest;

    public ConsListBlocks(Blocks first, ListBlocks rest) {
        this.first = first;
        this.rest = rest;
    }

    public WorldImage drawListBlocks() {
        return new OverlayImages(first.drawImage(), rest.drawListBlocks());
    }

    public boolean getHit(PlayerBlock playerblock) {
        if (playerblock.hitBlocksX(first) && playerblock.hitBlocksY(first)) {
            return true;
        } else {
            return rest.getHit(playerblock);
        }
    }

    public ListBlocks fall() {
        return new ConsListBlocks(first.fall(), rest.fall());
    }

}
