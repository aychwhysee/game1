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

    public int width = 40; // block width (twice as wide as falling blocks)
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

    public int hitBlockOne(Blocks block_one) {
        if (hitBlockOneX(block_one) && hitBlockOneY(block_one)) {
            return 1;
        } else if ((!(hitBlockOneX(block_one))) && hitBlockOneY(block_one)) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean hitBlockOneX(Blocks block_one) {
        return (block_one.posn.x + block_one.width / 2 >= this.posn.x)
                && (block_one.posn.x + block_one.width / 2 <= this.posn.x + this.width);
    }

    public boolean hitBlockOneY(Blocks block_one) {
        return block_one.posn.y + block_one.height > this.posn.y;
    }

    public int hitBlockTwo(Blocks block_two) {
        if (hitBlockTwoX(block_two) && hitBlockTwoY(block_two)) {
            return 1;
        } else if ((!(hitBlockTwoX(block_two))) && hitBlockTwoY(block_two)) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean hitBlockTwoX(Blocks block_two) {
        return (block_two.posn.x + block_two.width / 2 >= this.posn.x)
                && (block_two.posn.x + block_two.width / 2 <= this.posn.x + this.width);
    }

    public boolean hitBlockTwoY(Blocks block_two) {
        return block_two.posn.y + block_two.height > this.posn.y;
    }

    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
}
