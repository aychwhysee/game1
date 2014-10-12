package game1;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class Board {

    public int b_width;
    public int b_height;
    
    public IColor color = new Black();

    public Board(int width, int height) {
        this.b_width = b_width;
        this.b_height = b_height;
    }

    public WorldImage drawImage() {
        return new RectangleImage(new Posn(150, 150), b_width, b_height, color);
    }

}
