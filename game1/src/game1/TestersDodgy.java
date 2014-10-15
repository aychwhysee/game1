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

    Blocks bt1 = randomBlocks(85, 115, 470, 510);
    Blocks bf1 = randomBlocks(25, 75, 300, 340);
    Blocks brt1 = new Blocks(200, 500, 10);

    DodgyWorld p1w = new DodgyWorld(listblocks, this.p1, speed, frames, score,
            interval, gameOver);
    DodgyWorld p1leftw = new DodgyWorld(listblocks, this.p1left, speed, frames,
            score, interval, gameOver);
    DodgyWorld p1rightw = new DodgyWorld(listblocks, this.p1right, speed, frames,
            score, interval, gameOver);

    // Test the move method move in the PlayerBlock class
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

    public Random random = new Random();

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

    public boolean testFall(Tester t) {
        return true;
    }

    public boolean testWorldEnds(Tester t) {
        return true;
    }

    public boolean testOnTick(Tester t) {
        return true;
    }

    public static void main(String[] args) {
        TestersDodgy td = new TestersDodgy();
        // Only shows the failed test results
        Tester.runReport(td, false, false);
    }
}
