package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class TestersDodgy {
    
    public boolean testMove(Tester t) {
        return true;
    }
    
    public boolean testOnKeyEvent(Tester t) {
        return true;
    }
    
    public boolean testHitBlocksX(Tester t) {
        return true;
    }
    
    public boolean testHitBlocksY(Tester t) {
        return true;
    }
    
    public boolean testHitBlocks(Tester t) {
        return true;
    }
    
    public boolean testRandomX(Tester t) {
        return true;
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
