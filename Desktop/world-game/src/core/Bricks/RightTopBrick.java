package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class RightTopBrick {
    TETile[][] tiles;

    public RightTopBrick(int pattern) {
        if (pattern % 2 == 0) {
            tiles = rT0();
        }
        if (pattern % 2 == 1) {
            tiles = rT1();
        }
    }

    // Tested
    private TETile[][] rT0() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int y = 1; y < 8; y++) {
            for (int x = 1; x < 7; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 2; y < 7; y++) {
            for (int x = 2; x < 6; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[2][1] = Tileset.FLOOR;
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[1][2] = Tileset.FLOOR;
        newTiles[0][1] = Tileset.WALL;
        newTiles[0][3] = Tileset.WALL;
        newTiles[1][0] = Tileset.WALL;
        newTiles[3][0] = Tileset.WALL;
        return newTiles;
    }

    // Tested
    private TETile[][] rT1() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 0; x < 8; x++) {
            newTiles[x][0] = Tileset.WALL;
            newTiles[x][7] = Tileset.WALL;
        }
        for (int x = 2; x < 6; x++) {
            newTiles[x][2] = Tileset.WALL;
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        for (int y = 2; y < 7; y++) {
            newTiles[2][y] = Tileset.WALL;
        }
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[2][0] = Tileset.FLOOR;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}