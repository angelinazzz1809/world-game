package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class LeftBrick {
    TETile[][] tiles;

    public LeftBrick(int pattern) {
        if (pattern % 5 == 0) {
            tiles = lB0();
        }
        if (pattern % 5 == 1) {
            tiles = lB1();
        }
        if (pattern % 5 == 2) {
            tiles = lB2();
        }
        if (pattern % 5 == 3) {
            tiles = lB3();
        }
        if (pattern % 5 == 4) {
            tiles = lB4();
        }
    }

    // Tested
    private TETile[][] lB0() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int y = 0; y < 8; y++) {
            newTiles[1][y] = Tileset.WALL;
            newTiles[2][y] = Tileset.FLOOR;
            newTiles[3][y] = Tileset.WALL;
        }
        for (int x = 0; x < 2; x++) {
            newTiles[x][1] = Tileset.WALL;
            newTiles[x][2] = Tileset.FLOOR;
            newTiles[x][3] = Tileset.WALL;
        }
        return newTiles;
    }

    // Tested
    private TETile[][] lB1() {
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
        for (int x = 2; x < 7; x++) {
            newTiles[x][1] = Tileset.FLOOR;
            newTiles[x][2] = Tileset.FLOOR;
        }
        for (int y = 1; y < 7; y++) {
            newTiles[1][y] = Tileset.FLOOR;
        }
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[2][6] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[3][4] = Tileset.NOTHING;
        return newTiles;
    }

    // Tested
    private TETile[][] lB2() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 4; x < 8; x++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int x = 2; x < 6; x++) {
            newTiles[x][4] = Tileset.FLOOR;
            newTiles[x][6] = Tileset.FLOOR;
        }
        for (int y = 4; y < 8; y++) {
            newTiles[0][y] = Tileset.NOTHING;
            newTiles[7][y] = Tileset.NOTHING;
        }
        for (int y = 0; y < 4; y++) {
            newTiles[2][y] = Tileset.FLOOR;
        }
        newTiles[5][5] = Tileset.FLOOR;
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[1][2] = Tileset.FLOOR;
        newTiles[0][0] = Tileset.NOTHING;
        newTiles[7][3] = Tileset.NOTHING;
        return newTiles;
    }

    private TETile[][] lB3() {
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
        for (int y = 1; y < 7; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        newTiles[2][7] = Tileset.FLOOR;
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[2][2] = Tileset.WALL;
        newTiles[3][3] = Tileset.WALL;
        newTiles[4][4] = Tileset.WALL;
        newTiles[5][5] = Tileset.WALL;
        return newTiles;
    }

    private TETile[][] lB4() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int y = 4; y < 8; y++) {
            newTiles[0][y] = Tileset.NOTHING;
            newTiles[1][y] = Tileset.WALL;
        }
        for (int x = 3; x < 8; x++) {
            newTiles[x][0] = Tileset.WALL;
            newTiles[x][6] = Tileset.WALL;
        }
        for (int x = 4; x < 8; x++) {
            newTiles[x][7] = Tileset.NOTHING;
        }
        for (int x = 2; x < 5; x++) {
            newTiles[x][4] = Tileset.WALL;
        }
        for (int y = 0; y < 7; y++) {
            newTiles[7][y] = Tileset.WALL;
        }
        newTiles[4][2] = Tileset.WALL;
        newTiles[4][1] = Tileset.WALL;
        newTiles[0][3] = Tileset.WALL;
        newTiles[1][3] = Tileset.WALL;
        newTiles[1][1] = Tileset.WALL;
        newTiles[0][1] = Tileset.WALL;
        newTiles[1][0] = Tileset.WALL;
        newTiles[0][0] = Tileset.NOTHING;
        newTiles[3][7] = Tileset.WALL;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}