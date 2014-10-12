package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class BlockTwo {

    public Posn posn;

    public int speed;
    public int b_width; // Board width
    public int b_height; // Board height

    public final int width = 20; // Block width
    public final int height = 20; // Block height

    public IColor color = new Red();

    public Random random = new Random();
    
    public BlockOne o;

    public BlockTwo(int b_width, int b_height, int speed) {
        this.b_width = b_width;
        this.b_height = b_height;
        this.speed = speed;
        this.posn = new Posn(randomX(b_width), 0);
    }
    
    private BlockTwo(Posn posn, int b_width, int b_height, int speed) {
        this.posn = posn;
        this.b_width = b_width;
        this.b_height = b_height;
        this.speed = speed;
    }

    private int randomX(int b_width) {
        // Not sure if this actually will do what it's supposed to do.
        // Supposed to check if block one and two are in the same spot,
        // and if so, remake block two at a different spot...
        // Only way to find out is testing! Somehow.
        BlockOne block_one = new BlockOne(o.width, o.height, o.speed);
        if (this.sameX(block_one)) {
            randomX(b_width);
        }
        return random.nextInt(b_width - (width * 2)) + width;
    }
    
    public boolean sameX(BlockOne block_one) {
        return this.posn.x == block_one.posn.x;
    }

    public BlockTwo fall() {
        return new BlockTwo(new Posn(this.posn.x, this.posn.y + speed),
                this.b_width, this.b_height, this.speed);
    }
    
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }

}
