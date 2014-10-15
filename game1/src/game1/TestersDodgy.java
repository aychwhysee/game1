package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class TestersDodgy {

    public Blocks blocks;
    public ListBlocks listblocks;
    public PlayerBlock playerblock;
    public int speed;
    public int frames;
    public int score;
    public int interval;
    public boolean gameOver;

    public static Random rand = new Random();

    public static int randomIntX(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static int randomIntY(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    // Generates two random blocks within a range (for testing purposes)
    public static Blocks randomBlocks(int minX, int maxX, int minY, int maxY) {
        return new Blocks(new Posn(randomIntX(minX, maxX), (randomIntY(minY, maxY))),
                new Posn(randomIntX(minX, maxX), randomIntY(minY, maxY)),
                200, 500, 10);
    }

    PlayerBlock p1 = new PlayerBlock(new Posn(100, 490), 200, 500);
    PlayerBlock p1left = new PlayerBlock(new Posn(80, 490), 200, 500);
    PlayerBlock p1right = new PlayerBlock(new Posn(120, 490), 200, 500);

    // Since we're only using these to test for move, the other arguments
    // in the constructor can just be nothing
    DodgyWorld p1w = new DodgyWorld(listblocks, this.p1, speed, frames, score,
            interval, gameOver);
    DodgyWorld p1leftw = new DodgyWorld(listblocks, this.p1left, speed, frames,
            score, interval, gameOver);
    DodgyWorld p1rightw = new DodgyWorld(listblocks, this.p1right, speed, frames,
            score, interval, gameOver);

    Blocks bt1 = randomBlocks(95, 105, 480, 500);
    Blocks bf1 = randomBlocks(20, 70, 300, 340);

    Blocks brt1 = new Blocks(200, 500, 10);

    Blocks bff1 = new Blocks(new Posn(100, 0), new Posn(150, 0), 200, 500, 10);
    Blocks bff2 = new Blocks(new Posn(100, 10), new Posn(150, 10), 200, 500, 10);
    Blocks bff3 = new Blocks(new Posn(100, 20), new Posn(150, 20), 200, 500, 10);
    Blocks bff4 = new Blocks(new Posn(100, 30), new Posn(150, 30), 200, 500, 10);

    ListBlocks lb1 = new ConsListBlocks(
            new Blocks(new Posn(100, 460),
                    new Posn(150, 460),
                    200, 500, 10),
            new MTListBlocks());
    DodgyWorld ot1 = new DodgyWorld(this.lb1, this.p1, 10, 10, 10, 10, false);
    // Since we know that fall works, we don't need to make a new lb
    DodgyWorld ot2 = new DodgyWorld(this.lb1.fall(), this.p1, 10, 11, 11, 10, true);

    ListBlocks lb3 = new ConsListBlocks(
            new Blocks(new Posn(100, 300),
                    new Posn(150, 300),
                    200, 500, 10),
            new MTListBlocks());
    DodgyWorld ot3 = new DodgyWorld(this.lb3, this.p1, 10, 20, 10, 11, false);
    ListBlocks lb4 = new ConsListBlocks(
            new Blocks(new Posn(100, 10),
                    new Posn(150, 10),
                    200, 500, 10),
            lb3);
    DodgyWorld ot4 = new DodgyWorld(this.lb4, this.p1, 11, 21, 11, 12, false);

    public boolean testMove(Tester t) {
        return t.checkExpect(this.p1.move("left"),
                this.p1left, "test move - left" + "\n")
                && t.checkExpect(this.p1.move("right"),
                        this.p1right, "test move - right" + "\n")
                && t.checkExpect(this.p1left.move("right"),
                        this.p1, "test move - right" + "\n")
                && t.checkExpect(this.p1right.move("left"),
                        this.p1, "test move - left" + "\n")
                // Check that up and down do nothing (return this)
                && t.checkExpect(this.p1.move("up"),
                        this.p1, "test move - up" + "\n")
                && t.checkExpect(this.p1.move("down"),
                        this.p1, "test move - down" + "\n");
    }

    public boolean testOnKeyEvent(Tester t) {
        return t.checkExpect(this.p1w.onKeyEvent("left"),
                this.p1leftw, "test onKeyEvent - left" + "\n")
                && t.checkExpect(this.p1w.onKeyEvent("right"),
                        this.p1rightw, "test onKeyevent - right" + "\n")
                && t.checkExpect(this.p1leftw.onKeyEvent("right"),
                        this.p1w, "test onKeyEvent - right" + "\n")
                && t.checkExpect(this.p1rightw.onKeyEvent("left"),
                        this.p1w, "test onKeyEvent - left" + "\n")
                // Check that up and down do nothing
                && t.checkExpect(this.p1w.onKeyEvent("up"),
                        this.p1w, "test onKeyEvent - up" + "\n")
                && t.checkExpect(this.p1w.onKeyEvent("down"),
                        this.p1w, "test onKeyEvent - down" + "\n");
    }

    public boolean testHitBlocksX(Tester t) {
        return t.checkExpect(this.p1.hitBlocksX(bt1),
                true, "test hitBlocksX random")
                && t.checkExpect(this.p1.hitBlocksX(bt1),
                        true, "test hitBlocksX random")
                && t.checkExpect(this.p1.hitBlocksX(bt1),
                        true, "test hitBlocksX random")
                && t.checkExpect(this.p1.hitBlocksX(bt1),
                        true, "test hitBlocksX random")
                && t.checkExpect(this.p1.hitBlocksX(bt1),
                        true, "test hitBlocksX random")
                && t.checkExpect(this.p1.hitBlocksX(bf1),
                        false, "test hitBlockX random")
                && t.checkExpect(this.p1.hitBlocksX(bf1),
                        false, "test hitBlockX random")
                && t.checkExpect(this.p1.hitBlocksX(bf1),
                        false, "test hitBlockX random")
                && t.checkExpect(this.p1.hitBlocksX(bf1),
                        false, "test hitBlockX random")
                && t.checkExpect(this.p1.hitBlocksX(bf1),
                        false, "test hitBlockX random");
    }

    public boolean testHitBlocksY(Tester t) {
        return t.checkExpect(this.p1.hitBlocksY(bt1),
                true, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bt1),
                        true, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bt1),
                        true, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bt1),
                        true, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bt1),
                        true, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bf1),
                        false, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bf1),
                        false, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bf1),
                        false, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bf1),
                        false, "test hitBlocksY random")
                && t.checkExpect(this.p1.hitBlocksY(bf1),
                        false, "test hitBlocksY random");
    }

    // Will be used for testRandomX
    public boolean checkRandomX() {
        return (this.brt1.randomX(200) > 10 && this.brt1.randomX(200) < 190);
    }

    public boolean testRandomX(Tester t) {
        return t.checkExpect(this.checkRandomX(),
                true, "test randomX")
                && t.checkExpect(this.checkRandomX(),
                        true, "test randomX")
                && t.checkExpect(this.checkRandomX(),
                        true, "test randomX")
                && t.checkExpect(this.checkRandomX(),
                        true, "test randomX")
                && t.checkExpect(this.checkRandomX(),
                        true, "test randomX");
    }

    // Since we now know that randomX works, we can just plug in whatever
    // number for posn.x and posn2.x when testing for fall.
    public boolean testFall(Tester t) {
        return t.checkExpect(this.bff1.fall(),
                this.bff2, "test fall" + "\n")
                && t.checkExpect(this.bff2.fall(),
                        this.bff3, "test fall" + "\n")
                && t.checkExpect(this.bff3.fall(),
                        this.bff4, "test fall" + "\n")
                && t.checkExpect(this.bff1.fall().fall(),
                        this.bff3, "test fall twice" + "\n")
                && t.checkExpect(this.bff1.fall().fall().fall(),
                        this.bff4, "test fall thrice" + "\n");
    }

    // Test just the gameOver, score++, and frames part of onTick
    public boolean testOnTick1(Tester t) {
        return t.checkExpect(this.ot1.onTick(),
                this.ot2, "test onTick gameOver, score, frames");
    }

    // Test the speed, interval, ListBlocks part of onTick
    public boolean testOnTick2(Tester t) {
        return t.checkExpect(this.ot3.onTick(),
                this.ot4, "test onTick speed, interval, ListBlocks");
//        return true;
        // It works. The test passes (technically), but it'll say it doesn't when run
        // because I can't figure out the new random X co-ordinates
        // the game will spit out when a new pair of blocks are added
        // on to ListBlocks. Everything else matches - speed, interval,
        // and adding a new set of random blocks.
    }

    // Since we know onTick can successfully change gameOver, we can just
    // set gameOver to be true for the purpose of this test.
    public boolean testWorldEnds(Tester t) {
        return t.checkExpect(this.ot2.worldEnds(),
                new WorldEnd(true, new OverlayImages(this.ot2.makeImage(),
                                new TextImage(new Posn(100, 250),
                                        ("Game over! Your score is " + ot2.score),
                                        13,
                                        new White())))) &&
                t.checkExpect(this.ot3.worldEnds(),
                        new WorldEnd(false, this.ot3.makeImage()));
    }

    public static void main(String[] args) {
        TestersDodgy td = new TestersDodgy();
        // Only shows the failed test results
        Tester.runReport(td, false, false);
    }
}
