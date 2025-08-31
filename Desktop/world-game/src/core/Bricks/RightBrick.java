package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class RightBrick {
    TETile[][] tiles;

    public RightBrick(int pattern) {
        if (pattern % 5 == 0) {
            tiles = rB0();
        }
        if (pattern % 5 == 1) {
            tiles = rB1();
        }
        if (pattern % 5 == 2) {
            tiles = rB2();
        }
        if (pattern % 5 == 3) {
            tiles = rB3();
        }
        if (pattern % 5 == 4) {
            tiles = rB4();
        }
    }

    // Tested
    private TETile[][] rB0() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        for (int y = 0; y < 4; y++) {
            newTiles[5][y] = Tileset.WALL;
        }
        for (int x = 0; x < 8; x++) {
            newTiles[x][0] = Tileset.WALL;
            newTiles[x][7] = Tileset.WALL;
        }
        for (int x = 6; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
            newTiles[x][1] = Tileset.WALL;
        }
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.FLOOR;
        return newTiles;
    }

    // Tested
    private TETile[][] rB1() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 3; y < 7; y++) {
            for (int x = 3; x < 6; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 4; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
        }
        for (int x = 5; x < 8; x++) {
            newTiles[x][2] = Tileset.FLOOR;
        }
        for (int y = 0; y < 5; y++) {
            newTiles[0][y] = Tileset.NOTHING;
            newTiles[2][y] = Tileset.FLOOR;
        }
        for (int y = 6; y < 8; y++) {
            newTiles[0][y] = Tileset.NOTHING;
            newTiles[2][y] = Tileset.FLOOR;
        }
        for (int y = 4; y < 8; y++) {
            newTiles[7][y] = Tileset.NOTHING;
        }
        return newTiles;
    }

    // Tested
    private TETile[][] rB2() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 4; y < 8; y++) {
            for (int x = 4; x < 8; x++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int x = 4; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
            newTiles[x][2] = Tileset.FLOOR;
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.NOTHING;
            newTiles[2][y] = Tileset.FLOOR;
        }
        newTiles[3][2] = Tileset.FLOOR;
        return newTiles;
    }

    private TETile[][] rB3() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 0; y < 8; y++) {
            newTiles[0][y] = Tileset.NOTHING;
        }
        for (int x = 2; x < 6; x++) {
            newTiles[x][1] = Tileset.FLOOR;
            newTiles[x][6] = Tileset.FLOOR;
        }
        for (int x = 1; x < 4; x++) {
            newTiles[x][3] = Tileset.NOTHING;
            newTiles[x][4] = Tileset.NOTHING;
        }
        for (int y = 1; y < 7; y++) {
            newTiles[5][y] = Tileset.FLOOR;
        }
        for (int y = 4; y < 8; y++) {
            newTiles[7][y] = Tileset.NOTHING;
        }
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[6][2] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.FLOOR;
        newTiles[7][0] = Tileset.NOTHING;
        return newTiles;
    }

    private TETile[][] rB4() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 1; y < 6; y++) {
            newTiles[1][y] = Tileset.FLOOR;
            newTiles[6][y] = Tileset.FLOOR;
        }
        for (int x = 2; x < 6; x++) {
            newTiles[x][1] = Tileset.FLOOR;
            newTiles[x][5] = Tileset.FLOOR;
        }
        for (int x = 4; x < 8; x++) {
            newTiles[x][7] = Tileset.NOTHING;
        }
        newTiles[2][6] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.FLOOR;
        newTiles[0][7] = Tileset.NOTHING;
        newTiles[3][3] = Tileset.NOTHING;
        newTiles[4][3] = Tileset.NOTHING;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}