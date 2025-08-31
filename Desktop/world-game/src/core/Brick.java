package core;

import tileengine.TETile;
public class Brick {

    // Brick is similar to tile, but we can access its position as well

    private int xCoordinate;
    private int yCoordinate;
    private TETile tilePattern;
    public Brick(int x, int y, TETile pattern) {
        xCoordinate = x;
        yCoordinate = y;
        tilePattern = pattern;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return  yCoordinate;
    }

    public TETile getTilePattern() {
        return tilePattern;
    }

    public Brick changePattern(TETile newPattern) {
        return new Brick(xCoordinate, yCoordinate, newPattern);
    }
}
