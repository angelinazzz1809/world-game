package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static edu.princeton.cs.algs4.StdDraw.*;
import static edu.princeton.cs.algs4.StdDraw.textLeft;

public class DreamWorld {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private TETile[][] tiles;
    private TERenderer ter;
    private Integer flowersCollected = 0;
    private Integer[] avatarPosition;
    private Random rand = new Random();

    public DreamWorld() {
        ter = new TERenderer();
        initializeTiles();
        initializeAvatar(); // Initialize the avatar position
    }

    public void playDreamWorld() {
        beginning();
        render();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char next = StdDraw.nextKeyTyped();
                String nextString = Character.toString(next);

                if (nextString.equalsIgnoreCase("W")
                        || nextString.equalsIgnoreCase("A")
                        || nextString.equalsIgnoreCase("S")
                        || nextString.equalsIgnoreCase("D")) {
                    move(avatarPosition, nextString.toLowerCase());
                    ter.renderFrame(tiles);
                }
            }
        }
    }

    private void move(Integer[] avaPos, String direction) {
        Integer currX = avaPos[0];
        Integer currY = avaPos[1];

        switch (direction) {
            case "d": // RIGHT
                if (tiles[currX + 1][currY] == Tileset.FLOOR
                        || tiles[currX + 1][currY] == Tileset.FLOWER) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX + 1;
                    avatarPosNew[1] = currY;
                    if (tiles[currX + 1][currY] == Tileset.FLOWER) {
                        collectFlower(currX + 1, currY);
                    }
                    tiles[avatarPosition[0]][avatarPosition[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                }
                break;
            case "a": // LEFT
                if (tiles[currX - 1][currY] == Tileset.FLOOR
                        || tiles[currX - 1][currY] == Tileset.FLOWER) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX - 1;
                    avatarPosNew[1] = currY;
                    if (tiles[currX - 1][currY] == Tileset.FLOWER) {
                        collectFlower(currX - 1, currY);
                    }
                    tiles[avatarPosition[0]][avatarPosition[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                }
                break;
            case "w": // UP
                if (tiles[currX][currY + 1] == Tileset.FLOOR
                        || tiles[currX][currY + 1] == Tileset.FLOWER) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX;
                    avatarPosNew[1] = currY + 1;
                    if (tiles[currX][currY + 1] == Tileset.FLOWER) {
                        collectFlower(currX, currY + 1);
                    }
                    tiles[avatarPosition[0]][avatarPosition[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                }
                break;
            default: // DOWN
                if (tiles[currX][currY - 1] == Tileset.FLOOR
                        || tiles[currX][currY - 1] == Tileset.FLOWER) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX;
                    avatarPosNew[1] = currY - 1;
                    if (tiles[currX][currY - 1] == Tileset.FLOWER) {
                        collectFlower(currX, currY - 1);
                    }
                    tiles[avatarPosition[0]][avatarPosition[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                }
        }
    }

    private void updateAvatar(Integer[] pos) {
        tiles[pos[0]][pos[1]] = Tileset.AVATAR;
        avatarPosition = pos;
    }

    private void initializeAvatar() {
        ArrayList<Integer[]> floors = findAllFloor(tiles);
        int randomIndex = rand.nextInt(floors.size());
        avatarPosition = floors.get(randomIndex);
        tiles[avatarPosition[0]][avatarPosition[1]] = Tileset.AVATAR; // Set the avatar tile
    }

    private void collectFlower(int x, int y) {
        tiles[x][y] = Tileset.FLOOR; // Replace the flower tile with a floor tile
        flowersCollected++;
        if (flowersCollected == 10) {
            ending();
            exitDreamWorld(); // Set flag to indicate all flowers are collected
        }
    }

    private void exitDreamWorld() {
        WorldGame original = new WorldGame();
        original.getBackFromDreamWorld();
    }

    private void initializeTiles() {
        tiles = new TETile[WIDTH][HEIGHT];
        // Initialize all tiles as FLOOR
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                tiles[x][y] = Tileset.WALL;
            }
        }
        for (int x = 1; x < WIDTH - 1; x++) {
            for (int y = 1; y < HEIGHT - 1; y++) {
                tiles[x][y] = Tileset.FLOOR;
            }
        }
        // Randomly place 5 FLOWER tiles
        int flowersPlaced = 0;
        while (flowersPlaced < 10) {
            int x = rand.nextInt(WIDTH - 2) + 1;
            int y = rand.nextInt(HEIGHT - 2) + 1;
            tiles[x][y] = Tileset.FLOWER;
            flowersPlaced++;
        }
    }

    private ArrayList<Integer[]> findAllFloor(TETile[][] t) {
        int width = t.length;
        int height = t[0].length;

        ArrayList<Integer[]> storage = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int  y = 0; y < height; y++) {
                if (t[x][y] == Tileset.FLOOR) {
                    Integer[] coordinate = new Integer[2];
                    coordinate[0] = x;
                    coordinate[1] = y;
                    storage.add(coordinate);
                }
            }
        }
        return storage;
    }

    public void render() {
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(tiles);
    }

    public TETile[][] getTiles() {
        return tiles;
    }

    private void beginning() {
        setXscale(0, 20);
        setYscale(0, 20);
        clear(Color.BLACK);
        setPenRadius(0.05);
        setPenColor(Color.WHITE);
        setFont();
        Font fontLarge = new Font("Monospaced", Font.PLAIN, 15);
        setFont(fontLarge);
        textLeft(2, 13, "I'm Sorry, you're trapped here!");
        textLeft(2, 11, "Princess AnDu wants all the flowers");
        textLeft(2, 9, "You can exit only when you collect all of them!");
        textLeft(2, 7, "BEGIN NOW!");
        StdDraw.show();
        StdDraw.pause(6000);
    }

    private void ending() {
        setXscale(0, 20);
        setYscale(0, 20);
        clear(Color.BLACK);
        setPenRadius(0.05);
        setPenColor(Color.WHITE);
        setFont();
        Font fontLarge = new Font("Monospaced", Font.PLAIN, 15);
        setFont(fontLarge);
        textLeft(2, 13, "Thanks for the flowers!");
        textLeft(2, 11, "Princess AnDu loves them");
        textLeft(2, 9, "You can now return to Game");
        textLeft(2, 7, "Good Luck!");
        StdDraw.show();
        StdDraw.pause(6000);
    }
}
