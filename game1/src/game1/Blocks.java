package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class Blocks {

    public Posn posn; // block one?
    public Posn posn2; // block two?

    public int speed;
    public int b_width; // Board width
    public int b_height; // Board height

    public final int width = 20; // Block width
    public final int height = 20; // Block height

    public IColor color1 = new Blue(); //block one
    public IColor color2 = new Red(); // block two

    public Random random = new Random();

    public Blocks(int b_width, int b_height, int speed) {
        this.b_width = b_width;
        this.b_height = b_height;
        this.speed = speed;
        this.posn = new Posn(randomX(b_width), 0);
        this.posn2 = new Posn(randomX(b_width), 0);
    }

    private Blocks(Posn posn, Posn posn2, int b_width, int b_height, int speed) {
        this.posn = posn;
        this.posn2 = posn2;
        this.b_width = b_width;
        this.b_height = b_height;
        this.speed = speed;
    }

    private int randomX(int b_width) {
        return random.nextInt(b_width - (width * 2)) + width;
    }
    
    // Need to make sure posn and posn2 are not the same...
    // Or I mean I could just have this property of the game be that
    // they don't HAVE to be separate from each other right
    // After all, I am in control...

    public Blocks fall() {
        return new Blocks(
                new Posn(this.posn.x, this.posn.y + speed),
                new Posn(this.posn2.x, this.posn.y + speed),
                this.b_width,
                this.b_height,
                this.speed);
    }

    public WorldImage drawImage() {
        return new OverlayImages(new RectangleImage(this.posn, this.width, this.height, this.color1),
                new RectangleImage(this.posn, this.width, this.height, this.color2));
        // return new RectangleImage(this.posn, this.width, this.height, this.color1);
    }

}
