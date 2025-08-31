package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class DownBrick {
    TETile[][] tiles;
    public DownBrick(int pattern) {
        if (pattern % 5 == 0) {
            tiles = dB0();
        }
        if (pattern % 5 == 1) {
            tiles = dB1();
        }
        if (pattern % 5 == 2) {
            tiles = dB2();
        }
        if (pattern % 5 == 3) {
            tiles = dB3();
        }
        if (pattern % 5 == 4) {
            tiles = dB4();
        }
    }

    // Tested
    private TETile[][] dB0() {
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
        for (int y = 0; y < 2; y++) {
            newTiles[1][y] = Tileset.WALL;
            newTiles[2][y] = Tileset.FLOOR;
            newTiles[3][y] = Tileset.WALL;
        }
        return newTiles;
    }

    // Tested
    private TETile[][] dB1() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 0; x < 8; x++) {
            newTiles[x][0] = Tileset.WALL;
            newTiles[x][2] = Tileset.WALL;
            newTiles[x][7] = Tileset.WALL;
        }

        for (int y = 1; y < 7; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
        }
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[1][2] = Tileset.FLOOR;
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.FLOOR;
        newTiles[6][2] = Tileset.FLOOR;
        return newTiles;
    }

    // Tested
    private TETile[][] dB2() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 4; y < 7; y++) {
            for (int x = 0; x < 3; x++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int y = 1; y < 6; y++) {
            for (int x = 4; x < 7; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 0; x < 8; x++) {
            newTiles[x][2] = Tileset.FLOOR;
            newTiles[x][7] = Tileset.NOTHING;
        }
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[2][1] = Tileset.FLOOR;
        return newTiles;
    }

    private TETile[][] dB3() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.WALL;
            }
        }
        for (int y = 2; y < 7; y++) {
            newTiles[1][y] = Tileset.FLOOR;
            newTiles[3][y] = Tileset.FLOOR;
            newTiles[5][y] = Tileset.FLOOR;
        }
        for (int y = 4; y < 8; y++) {
            newTiles[7][y] = Tileset.NOTHING;
        }
        for (int x = 1; x < 4; x++) {
            newTiles[x][1] = Tileset.FLOOR;
            newTiles[x][6] = Tileset.FLOOR;
        }
        for (int x = 5; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
            newTiles[x][2] = Tileset.FLOOR;
        }
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[0][2] = Tileset.FLOOR;
        newTiles[4][6] = Tileset.FLOOR;
        return newTiles;
    }

    private TETile[][] dB4() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = 0; x < 7; x++) {
            newTiles[x][7] = Tileset.WALL;
        }
        for (int y = 3; y < 7; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[6][y] = Tileset.WALL;
        }
        for (int y = 4; y < 8; y++) {
            newTiles[7][y] = Tileset.NOTHING;
        }
        for (int x = 4; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
            newTiles[x][1] = Tileset.WALL;
        }
        newTiles[0][0] = Tileset.NOTHING;
        newTiles[0][1] = Tileset.WALL;
        newTiles[1][1] = Tileset.WALL;
        newTiles[1][0] = Tileset.WALL;
        newTiles[3][0] = Tileset.WALL;
        newTiles[3][1] = Tileset.WALL;
        newTiles[7][3] = Tileset.WALL;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}