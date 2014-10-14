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

    public int score;
    public int speed;
    public int frames;

    public Blocks blocks;
    public PlayerBlock playerblock;

    public boolean gameOver;

    public DodgyWorld() {
        this(10);
    }

    public DodgyWorld(int speed) {
        super();
        this.blocks = new Blocks(b_width, b_height, speed);
        this.playerblock = new PlayerBlock(new Posn(b_width / 2, 490), b_width,
                b_height);
        this.speed = speed;
        this.frames = 0;
        this.score = 0;
        this.gameOver = false;
    }

    private DodgyWorld(Blocks blocks, PlayerBlock playerblock, int speed, int frames,
            int score, boolean gameOver) {
        this.blocks = blocks;
        this.playerblock = playerblock;
        this.speed = speed;
        this.frames = frames;
        this.score = score;
        this.gameOver = gameOver;
    }

    public World onTick() {
        if (this.playerblock.hitBlocks(this.blocks) == 1) {
            gameOver = true;
        } else {
            score++;
        }
        if (this.frames % 20 == 1) {
            new Blocks(b_width, b_height, speed).fall();
        }
        return new DodgyWorld(blocks.fall(), playerblock, speed + 1,
                frames + 1, score, gameOver);

    }

    public World onKeyEvent(String kee) {
        if (kee.equals("left") || (kee.equals("right"))) {
            return new DodgyWorld(blocks, playerblock.move(kee),
                    speed, frames, score, gameOver);
        } else {
            return this;
        }
    }

    public WorldEnd worldEnds() {
        if (gameOver) {
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(b_width / 2, b_height / 2),
                            ("Game over! Your score is " + this.score),
                            13,
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
        DodgyWorld dodgy = new DodgyWorld();
        dodgy.bigBang(200, 500, 0.15);
    }

}
