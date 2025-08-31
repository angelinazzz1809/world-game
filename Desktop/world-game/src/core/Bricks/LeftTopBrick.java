package core.Bricks;

import tileengine.TETile;
import tileengine.Tileset;

public class LeftTopBrick {
    TETile[][] tiles;

    public LeftTopBrick(int pattern) {
        if (pattern % 2 == 0) {
            tiles = lT0();
        }
        if (pattern % 2 == 1) {
            tiles = lT1();
        }
    }

    // Tested
    private TETile[][] lT0() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int x = 3; x < 8; x++) {
            newTiles[x][1] = Tileset.WALL;
            newTiles[x][2] = Tileset.FLOOR;
            newTiles[x][3] = Tileset.WALL;
        }
        for (int y = 4; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int y = 0; y < 3; y++) {
            newTiles[0][y] = Tileset.NOTHING;
            newTiles[1][y] = Tileset.WALL;
            newTiles[2][y] = Tileset.FLOOR;
        }
        for (int x = 4; x < 8; x++) {
            newTiles[x][0] = Tileset.NOTHING;
        }
        newTiles[0][3] = Tileset.NOTHING;
        newTiles[1][3] = Tileset.WALL;
        newTiles[2][3] = Tileset.WALL;
        newTiles[3][0] = Tileset.WALL;
        return newTiles;
    }

    //Tested
    private TETile[][] lT1() {
        // Every Brick is 8x8
        TETile[][] newTiles = new TETile[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                newTiles[x][y] = Tileset.NOTHING;
            }
        }
        for (int x = 0; x < 8; x++) {
            newTiles[x][1] = Tileset.WALL;
            newTiles[x][5] = Tileset.WALL;
        }
        for (int y = 2; y < 5; y++) {
            newTiles[0][y] = Tileset.WALL;
            newTiles[7][y] = Tileset.WALL;
            for (int x = 1; x < 7; x++) {
                newTiles[x][y] = Tileset.FLOOR;
            }
        }
        newTiles[2][0] = Tileset.FLOOR;
        newTiles[2][1] = Tileset.FLOOR;
        newTiles[7][2] = Tileset.FLOOR;
        newTiles[1][0] = Tileset.WALL;
        newTiles[3][0] = Tileset.WALL;
        return newTiles;
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }
}