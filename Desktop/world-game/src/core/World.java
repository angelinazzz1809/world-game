package core;

import core.Bricks.*;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private TETile[][] TILES;
    private long seed;
    private Random random;
    private Integer[] avatarPos;
    private ArrayList<Integer[]> dreamEntry = new ArrayList<>();

    public World(long seed) {

        // Fix Width = 80, Height = 48
        TILES = new TETile[80][48];

        // Take in Seed and make random
        this.seed = seed;
        this.random = new Random(this.seed);

        //Find the Fixed Matrix for the given Seed
        // This is a 10x6 matrix filled with integers
        // The integers will determine which BRICK to be applied
        int[][] matrix = randomnessMatrix();

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 6; y++) {
                int pattern = matrix[x][y];
                fillInTiles(pattern, x, y);
            }
        }
        ArrayList<Integer[]> floors = findAllFloor(TILES);
        avatarAndDream(random, floors);
    }

    // Find all floors in tiles
    private ArrayList<Integer[]> findAllFloor(TETile[][] tiles) {
        int width = tiles.length;
        int height = tiles[0].length;

        ArrayList<Integer[]> storage = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int  y = 0; y < height; y++) {
                if (tiles[x][y] == Tileset.FLOOR) {
                    Integer[] coordinate = new Integer[2];
                    coordinate[0] = x;
                    coordinate[1] = y;
                    storage.add(coordinate);
                }
            }
        }
        return storage;
    }

    // Generate AVATAR at Random Floor
    private void avatarAndDream(Random r, ArrayList<Integer[]> floors) {
        int randomIndex1 = r.nextInt(floors.size());
        Integer[] ap = floors.remove(randomIndex1);
        int randomIndex2 = r.nextInt(floors.size() - 1);
        Integer[] dp1 = floors.get(randomIndex2);
        int randomIndex3 = r.nextInt(floors.size() - 2);
        Integer[] dp2 = floors.get(randomIndex3);
        updateAvatar(ap);
        updateDream(dp1);
        updateDream(dp2);
    }

    private void updateAvatar(Integer[] pos) {
        TILES[pos[0]][pos[1]] = Tileset.AVATAR;
        avatarPos = pos;
    }

    private void updateDream(Integer[] pos) {
        TILES[pos[0]][pos[1]] = Tileset.MOUNTAIN;
        dreamEntry.add(pos);
    }

    public Integer[] getAvatar() {
        return avatarPos;
    }

    public ArrayList<Integer[]> getDreamEntry() {
        return dreamEntry;
    }

    private void fillInTiles(int pattern, int startX, int startY) {
        TETile[][] brick = getBrick(pattern, startX, startY);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                TILES[startX * 8 + x][startY * 8 + y] = brick[x][y];
            }
        }
    }

    private TETile[][] getBrick(int pattern, int startX, int startY) {
        // Each kind of BRICK has 2-3 patterns
        // 8 kinds of BRICK: 1-LB, 2-LT, 3-RT, 4-RB, 5-R, 6-L, 7-U, 8-D
        if (startX == 0 && startY == 0) {
            LeftBottomBrick lbb = new LeftBottomBrick(pattern);
            return lbb.getTiles();
        } else if (startX == 0 && startY == 5) {
            LeftTopBrick ltb = new LeftTopBrick(pattern);
            return ltb.getTiles();
        } else if (startX == 9 && startY == 5) {
            RightTopBrick rtb = new RightTopBrick(pattern);
            return rtb.getTiles();
        } else if (startX == 9 && startY == 0) {
            RightBottomBrick rbb = new RightBottomBrick(pattern);
            return rbb.getTiles();
        } else if (startX == 0) {
            RightBrick rb = new RightBrick(pattern);
            return rb.getTiles();
        } else if (startX == 9) {
            LeftBrick lb = new LeftBrick(pattern);
            return lb.getTiles();
        } else if (startY % 2 == 0) {
            UpBrick ub = new UpBrick(pattern);
            return ub.getTiles();
        } else {
            DownBrick db = new DownBrick(pattern);
            return db.getTiles();
        }
    }

    private int randomNum() {
        return random.nextInt(5);
    }
    private int[][] randomnessMatrix() {
        int[][] matrix = new int[10][6];
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 6; y++) {
                matrix[x][y] = randomNum();
            }
        }
        return matrix;
    }
    public TETile[][] getTiles() {
        // return tiles
        return this.TILES;
    }
}
