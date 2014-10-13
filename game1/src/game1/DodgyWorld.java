package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgyWorld extends World {

    public static final int b_width = 200;
    public static final int b_height = 500;

    public int score = 0;
    public static int speed = 0;
    public int frames;

    public Blocks blocks;
    public PlayerBlock playerblock;

    public boolean gameOver;

    public DodgyWorld(Blocks blocks, PlayerBlock playerblock) {
        this.blocks = blocks;
        this.playerblock = playerblock;
    }

    public World onTick() {
        if (this.playerblock.hitBlocks(this.blocks) == 1) {
            gameOver = true;
            return this.endOfWorld("Game Over!");
        } else {
            score++;
            speed++;
            return new DodgyWorld(blocks.fall(), this.playerblock);
        }

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
                new Posn(160, 25),
                ("Score: " + this.score),
                14,
                new Green());
    }

    public WorldImage makeImage() {
        return new OverlayImages(board(), new OverlayImages(blocks.drawImage(),
                new OverlayImages(playerblock.drawImage(), scoreImage())));
    }

    public static void main(String[] args) {
        DodgyWorld dodgy = new DodgyWorld(
        new Blocks(b_width, b_height, speed),
                new PlayerBlock(new Posn(b_width/2, 480), b_width, b_height ));
        
        dodgy.bigBang(200, 500, 0.5);

    }

}
