package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgyWorld extends World {

    public final int b_width = 200;
    public final int b_height = 500;

    public int score;
    public int speed;
    public int frames;

    public Blocks blocks;
    public PlayerBlock playerblock;

    public boolean gameOver;

    public DodgyWorld(Blocks blocks, PlayerBlock playerblock) {
        this.blocks = blocks;
        this.playerblock = playerblock;
    }

    public World onTick() {

    }

    public World onKeyEvent(String kee) {
        if (kee.equals("left") || (kee.equals("right"))) {
            return new DodgyWorld(this.blocks, playerblock.move(kee));
        } else {
            return this;
        }
    }

    public WorldEnd worldEnds() {
        if (gameOver) {
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(b_width / 2, b_height / 2),
                            ("Game over! Your score is " + this.score),
                            16,
                            new White())));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    private WorldImage board() {
        return new RectangleImage(
                new Posn(b_width / 2, b_height / 2),
                b_width,
                b_height,
                new Black());
    }

    public WorldImage scoreImage() {
        return new TextImage(
                new Posn(180, 25),
                ("Score: " + this.score),
                14,
                new Green());
    }
    
    public WorldImage makeImage() {
        
    }

    public static void main(String[] args) {

    }

}
