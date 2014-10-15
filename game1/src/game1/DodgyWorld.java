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
    public int interval = 10;

    public PlayerBlock playerblock;
    public ListBlocks listblocks;

    public boolean gameOver;

    public DodgyWorld() {
        this(5);
    }

    public DodgyWorld(int speed) {
        super();
        this.listblocks = new MTListBlocks();
        this.playerblock = new PlayerBlock(new Posn(b_width / 2, 490), b_width,
                b_height);
        this.speed = speed;
        this.frames = 0;
        this.interval = interval;
        this.score = 0;
        this.gameOver = false;
    }

    public DodgyWorld(ListBlocks listblocks, PlayerBlock playerblock, int speed,
            int frames, int score, int interval, boolean gameOver) {
        this.listblocks = listblocks;
        this.playerblock = playerblock;
        this.speed = speed;
        this.frames = frames;
        this.interval = interval;
        this.score = score;
        this.gameOver = gameOver;
    }

    public World onTick() {
        ListBlocks new_listblocks = this.listblocks;
        if (new_listblocks.getHit(playerblock)) {
            gameOver = true;
        }
        if (this.frames % (30 - interval) == 1) {
            Blocks newBlocks = new Blocks(b_width, b_height, speed);
            new_listblocks = new ConsListBlocks(newBlocks, new_listblocks);
            if (speed < 20) {
                speed++;
            }
            if (interval < 15) {
                interval++;
            }
        }
        score++;
        return new DodgyWorld(new_listblocks.fall(), playerblock, speed,
                frames + 1, score, interval, gameOver);

    }

    public World onKeyEvent(String kee) {
        if (kee.equals("left") || (kee.equals("right"))) {
            return new DodgyWorld(listblocks, playerblock.move(kee),
                    speed, frames, score, interval, gameOver);
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
        return new OverlayImages(board(), new OverlayImages(listblocks.drawListBlocks(),
                new OverlayImages(playerblock.drawImage(), scoreImage())));
    }

    public static void main(String[] args) {
        DodgyWorld dodgy = new DodgyWorld();
        dodgy.bigBang(200, 500, 0.15);
    }

}
