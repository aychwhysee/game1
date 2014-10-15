package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class PlayerBlock {

    public Posn posn;

    public int b_width; // board width
    public int b_height; // board height

    public int width = 30; // block width (twice as wide as falling blocks)
    public int height = 20; // block height (arbitrary graphical look number)

    public int playerSpeed = 20;

    public IColor color = new Yellow();

    public PlayerBlock(Posn posn, int b_width, int b_height) {
        this.posn = posn;
        this.b_width = b_width;
        this.b_height = b_height;
    }

    public PlayerBlock move(String kee) {
        if (kee.equals("left")) {
            return new PlayerBlock(new Posn(this.posn.x - playerSpeed, this.posn.y),
                    this.b_width, this.b_height);
        } else if (kee.equals("right")) {
            return new PlayerBlock(new Posn(this.posn.x + playerSpeed, this.posn.y),
                    this.b_width, this.b_height);
        } else {
            return this;
        }
    }

    public boolean hitBlocksX(Blocks blocks) {
        return ((blocks.posn.x + blocks.width / 2 >= this.posn.x - this.width/2)
                && (blocks.posn.x - blocks.width / 2 <= this.posn.x + this.width/2))
                || ((blocks.posn2.x + blocks.width / 2 >= this.posn.x - this.width/2)
                && (blocks.posn2.x - blocks.width / 2 <= this.posn.x + this.width/2));
    }

    public boolean hitBlocksY(Blocks blocks) {
        return ((blocks.posn.y + blocks.height >= this.posn.y - this.height/2)
                && (blocks.posn.y <= this.posn.y + this.height/2))
                || ((blocks.posn2.y + blocks.height >= this.posn.y - this.height/2)
                && (blocks.posn2.y <= this.posn.y + this.height/2));
    }

    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
}
