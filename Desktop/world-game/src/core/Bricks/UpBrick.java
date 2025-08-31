package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class UpBrick {
    TETile[][] tiles;

    public UpBrick(int pattern) {
        if (pattern % 5 == 0) {
            tiles = uB0();
        }
        if (pattern % 5 == 1) {
            tiles = uB1();
        }
        if (pattern % 5 == 2) {
            tiles = uB2();
        }
        if (pattern % 5 == 3) {
            tiles = uB3();
        }
        if (pattern % 5 == 4) {
            tiles = uB4();
        }
    }

    // Tested
    private TETile[][] uB0() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int x = 0; x < 8; x++) {
            newTiles[x][1] = Tileset.WALL;
            newTiles[x][2] = Tileset.FLOOR;
            newTiles[x][3] = Tileset.WALL;
        }
        for (int y = 3; y < 8; y++) {
            newTiles[1][y] = Tileset.WALL;
            newTiles[2][y] = Tileset.FLOOR;
            newTiles[3][y] = Tileset.WALL;
        }
        return newTiles;
    }

    // Tested
    private TETile[][] uB1() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 1; x < 7; x++) {
            newTiles[x][0] = Tileset.WALL;
            newTiles[x][7] = Tileset.WALL;
        }
        for (int y = 3; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[6][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.NOTHING;
        }
        for (int y = 0; y < 2; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        newTiles[0][7] = Tileset.NOTHING;
        newTiles[7][3] = Tileset.WALL;
        newTiles[1][6] = Tileset.WALL;
        newTiles[2][7] = Tileset.FLOOR;
        return newTiles;
    }

    // Tested
    private TETile[][] uB2() {
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
        for (int y = 0; y < 2; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        newTiles[7][2] = Tileset.FLOOR;
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        return newTiles;
    }

    private TETile[][] uB3() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 1; x < 8; x++) {
            newTiles[x][0] = Tileset.WALL;
            newTiles[x][5] = Tileset.WALL;
            newTiles[x][7] = Tileset.WALL;
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        newTiles[1][6] = Tileset.WALL;
        newTiles[4][1] = Tileset.WALL;
        newTiles[4][2] = Tileset.WALL;
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[6][5] = Tileset.FLOOR;
        return newTiles;
    }

    private TETile[][] uB4() {
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
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.FLOOR;

        newTiles[1][6] = Tileset.WALL;
        newTiles[1][4] = Tileset.WALL;
        newTiles[1][5] = Tileset.WALL;
        newTiles[2][5] = Tileset.WALL;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}