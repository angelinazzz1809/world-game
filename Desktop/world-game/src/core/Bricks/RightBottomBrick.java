package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class RightBottomBrick {
    TETile[][] tiles;

    public RightBottomBrick(int pattern) {
        if (pattern % 2 == 0) {
            tiles = rB0();
        }
        if (pattern % 2 == 1) {
            tiles = rB1();
        }
    }

    private TETile[][] rB0() {
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
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        for (int x = 3; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
            newTiles[x][1] = Tileset.NOTHING;
            newTiles[x][2] = Tileset.WALL;
        }
        newTiles[0][7] = Tileset.NOTHING;
        newTiles[1][6] = Tileset.WALL;
        newTiles[2][1] = Tileset.WALL;
        newTiles[2][2] = Tileset.WALL;
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        return newTiles;
    }

    private TETile[][] rB1() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int x = 0; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
            newTiles[x][1] = Tileset.WALL;
        }
        for (int y = 2; y < 7; y++) {
            for (int x = 1; x < 7; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int y = 3; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        for (int x = 3; x < 7; x++) {
            newTiles[x][7] = Tileset.WALL;
        }
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[1][7] = Tileset.WALL;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.WALL;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}