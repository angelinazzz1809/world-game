package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class LeftBottomBrick {

    private TETile[][] tiles;

    public LeftBottomBrick(int pattern) {
        if (pattern % 2 == 0) {
            tiles = lB0();
        }
        if (pattern % 2 == 1) {
            tiles = lB1();
        }
    }


    // Tested
    private TETile[][] lB0() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int x = 0; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
        }
        for (int x = 1; x < 8; x++) {
            newTiles[x][1] = Tileset.WALL;
        }
        for (int x = 2; x < 8; x++) {
            newTiles[x][2] = Tileset.FLOOR;
        }
        for (int x = 3; x < 8; x++) {
            newTiles[x][3] = Tileset.WALL;
        }
        for (int x = 4; x < 8; x++) {
            for (int y = 4; y < 8; y++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.NOTHING;
        }
        for (int y = 1; y < 8; y++) {
            newTiles[1][y] = Tileset.WALL;
        }
        for (int y = 2; y < 8; y++) {
            newTiles[2][y] = Tileset.FLOOR;
        }
        for (int y = 3; y < 8; y++) {
            newTiles[3][y] = Tileset.WALL;
        }
        return newTiles;
    }

    // Tested
    private TETile[][] lB1() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 0; x < 8; x++) {
            newTiles[x][0] = Tileset.WALL;
            newTiles[x][7] = Tileset.WALL;
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        newTiles[7][2] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}