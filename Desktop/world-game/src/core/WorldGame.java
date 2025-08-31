package core;

import static edu.princeton.cs.algs4.StdDraw.*;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WorldGame {
    // All the Game Interactivity and Keyboard Input will be implemented in this class

    // Set the window SIZE of the GAME; able to change
    public static final int WIDTH = 80;
    public static final int HEIGHT = 48;
    private World WORLD;
    private long SEED;
    private TETile[][] TILES;
    private boolean GAMEON = false;
    private Integer[] AVATAR;
    private TERenderer TER;
    private ArrayList<Integer[]> MOUNTAIN;


    public WorldGame() {
        TER = new TERenderer();
        TER.initialize(WIDTH, HEIGHT);
    }

    // Manage Input
    // N - new WorldGame;
    // W/A/S/D - Position Change within the current WorldGame;
    // L - load SAVED WorldGame; (saved as a String)
    // :Q - Exit and Save the current WorldGame;

    /**
     * Spine of this GAME
     * interact with every new keyboard input
     * call on other methods when needed
     */
    public void manageWorldFromKeys() {
        if (!GAMEON) {
            menuPage();
        }
        // Manage Keyboard Input
        while (true) {
            if (GAMEON) {
                visualize(TILES); // Render the world
                displayTime();
                drawHUD();
            }
            if (hasNextKeyTyped()) {
                char next = nextKeyTyped();
                String nextString = Character.toString(next);

                if (nextString.equalsIgnoreCase("R")) {
                    GAMEON = false;
                    menuPage();
                    manageWorldFromKeys();
                }
                if (nextString.equalsIgnoreCase("I")) {
                    instructionPage();
                }
                if (GAMEON) {
                    // Quit and Save Game when GAMEON
                    if (nextString.equals(":")) {
                        while (true) {
                            if (hasNextKeyTyped()) {
                                String afterColon = Character.toString(nextKeyTyped());
                                if (afterColon.equalsIgnoreCase("Q")) {
                                    save("save.txt", SEED, AVATAR);
                                    System.exit(0);
                                }
                                if (afterColon.equalsIgnoreCase("R")) {
                                    save("save.txt", SEED, AVATAR);
                                    GAMEON = false;
                                    menuPage();
                                    manageWorldFromKeys();
                                }
                            }
                        }
                    }
                    // MOVEMENT
                    if (nextString.equalsIgnoreCase("W")
                            || nextString.equalsIgnoreCase("A")
                            || nextString.equalsIgnoreCase("S")
                            || nextString.equalsIgnoreCase("D")) {
                        move(AVATAR, nextString.toLowerCase());
                    }
                } else {
                    if (nextString.equalsIgnoreCase("N")) {
                        getSeed();
                        WORLD = new World(SEED);
                        TILES = WORLD.getTiles();
                        AVATAR = WORLD.getAvatar();
                        MOUNTAIN = WORLD.getDreamEntry();
                        TER.initialize(WIDTH, HEIGHT);
                        GAMEON = true;
                    } else if (nextString.equalsIgnoreCase("Q")) {
                        // Menu Page Exit
                        System.exit(0);
                    } else if (nextString.equalsIgnoreCase("L")) {
                        String saved = retrieve("save.txt");
                        if (saved == null) {
                            errorPage();
                        } else {
                            GAMEON = true;
                            SEED = getSeedFromLoad(saved);
                            WORLD = getWorldFromLoad(saved);
                            TILES = WORLD.getTiles();
                            AVATAR = updateAvatarFromLoad(saved, TILES);
                            updateAvatar(AVATAR);
                            TER.initialize(WIDTH, HEIGHT);
                        }
                    }
                }
            }
            StdDraw.pause(50);
        }
    }

    public void getBackFromDreamWorld() {
        // Load the most recent saved
        String saved = retrieve("saveDreamWorld.txt");
        SEED = getSeedFromLoad(saved);
        WORLD = getWorldFromLoad(saved);
        TILES = WORLD.getTiles();
        AVATAR = updateAvatarFromLoad(saved, TILES);
        updateAvatar(AVATAR);
        TER = new TERenderer();
        TER.initialize(WIDTH, HEIGHT);
        TER.drawTiles(TILES);
        GAMEON = true;
        manageWorldFromKeys();
    }

    private void drawHUD() {
        // Check if the mouse position is within the world area
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 14));

        int mouseX = (int) StdDraw.mouseX();
        int mouseY = (int) StdDraw.mouseY(); // Adjust for yOffset

        // Check if mouse is within bounds and display tile description
        if (mouseX >= 0 && mouseX < WIDTH && mouseY >= 0 && mouseY < HEIGHT) {
            StdDraw.textLeft(1, HEIGHT - 1, "Tile: " + getTileDescription(mouseX, mouseY));
        }
        StdDraw.text(40, HEIGHT - 1, "Press I for More Info");
        StdDraw.show();
    }

    private void displayTime() {
        // Display current date and time
        StdDraw.setPenColor(Color.WHITE);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        StdDraw.textRight(WIDTH - 1, HEIGHT - 1, formattedDateTime); // Right align date and time
        StdDraw.show();
    }

    private String getTileDescription(int x, int y) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            if (AVATAR[0] == x && AVATAR[1] == y) {
                return "you"; // If the mouse is over the Avatar, return "you"
            } else {
                return TILES[x][y].description();
            }
        }
        return "";
    }

    private void move(Integer[] avatarCurrPos, String direction) {
        int currX = avatarCurrPos[0];
        int currY = avatarCurrPos[1];
        switch (direction) {
            case "d": // RIGHT
                if (TILES[currX + 1][currY] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX + 1;
                    avatarPosNew[1] = currY;
                    TILES[AVATAR[0]][AVATAR[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                } else if (TILES[currX + 1][currY] == Tileset.MOUNTAIN) {
                    enterDreamWorld();
                }
                break;
            case "a": // LEFT
                if (TILES[currX - 1][currY] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX - 1;
                    avatarPosNew[1] = currY;
                    TILES[AVATAR[0]][AVATAR[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                } else if (TILES[currX - 1][currY] == Tileset.MOUNTAIN) {
                    enterDreamWorld();
                }
                break;
            case "w": // UP
                if (TILES[currX][currY + 1] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX;
                    avatarPosNew[1] = currY + 1;
                    TILES[AVATAR[0]][AVATAR[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                } else if (TILES[currX][currY + 1] == Tileset.MOUNTAIN) {
                    enterDreamWorld();
                }
                break;
            default: // DOWN
                if (TILES[currX][currY - 1] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX;
                    avatarPosNew[1] = currY - 1;
                    TILES[AVATAR[0]][AVATAR[1]] = Tileset.FLOOR;
                    updateAvatar(avatarPosNew);
                } else if (TILES[currX][currY - 1] == Tileset.MOUNTAIN) {
                    enterDreamWorld();
                }
        }
    }

    private void enterDreamWorld() {
        save("saveDreamWorld.txt", SEED, AVATAR);
        DreamWorld dw = new DreamWorld();
        dw.playDreamWorld();
    }
    private void updateAvatar(Integer[] pos) {
        TILES[pos[0]][pos[1]] = Tileset.AVATAR;
        AVATAR = pos;
    }

    // Helper method to get SEED after "N" is typed
    private long getSeed() {
        seedPage();
        // N is typed, so we assume that the next come up is integer
        StringBuilder seedString = new StringBuilder();
        // iterate until S appears
        long x = 48;
        int digit = 0;
        while (true) {
            if (hasNextKeyTyped()) {
                char next = nextKeyTyped();
                if (Character.toString(next).equalsIgnoreCase("R")) {
                    manageWorldFromKeys();
                } else if (Character.toString(next).equalsIgnoreCase("S")) {
                    if (digit == 0) {
                        errorPage();
                        manageWorldFromKeys();
                    } else {
                        // return SEED
                        long seed = Long.parseLong(seedString.toString());
                        this.SEED = seed;
                        return seed;
                    }
                } else if (Character.isDigit(next)) {
                    seedString.append(next);
                    setPenColor(Color.WHITE);
                    Font fontLarge = new Font("Monospaced", Font.PLAIN, 30);
                    setFont(fontLarge);
                    text(x, 35, Character.toString(next));
                    x += 1;
                    digit += 1;
                    show();
                }
            }
        }
    }

    private static World getWorldFromLoad(String saved) {
        String[] info = saved.split(",");
        Long seed = Long.parseLong(info[0]);
        return new World(seed);
    }

    private static Long getSeedFromLoad(String saved) {
        String[] info = saved.split(",");
        return Long.parseLong(info[0]);
    }

    private static Integer[] updateAvatarFromLoad(String saved, TETile[][] tiles) {
        String[] info = saved.split(",");
        World world = getWorldFromLoad(saved);
        Integer[] oldAvatarPos = world.getAvatar();
        tiles[oldAvatarPos[0]][oldAvatarPos[1]] = Tileset.FLOOR;
        Integer avatarX = Integer.parseInt(info[1]);
        Integer avatarY = Integer.parseInt(info[2]);
        Integer[] avatarPos = new Integer[2];
        avatarPos[0] = avatarX;
        avatarPos[1] = avatarY;
        return avatarPos;
    }


    // For save and load part(L), ChatGPT Generated
    private static void save(String filePath, Long seed, Integer[] avatar) {
        Path path = Paths.get(filePath);
        String content = seed.toString() + ","
                + avatar[0].toString() + "," + avatar[1].toString();
        try {
            // Write the new content to the file
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
            System.out.println("File updated with new content.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isEmpty(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.size(path) == 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String retrieve(String filePath) {
        if (!isEmpty(filePath)) {
            Path path = Paths.get(filePath);
            try {
                // Read the existing content from the file
                byte[] oldContentBytes = Files.readAllBytes(path);
                return new String(oldContentBytes, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
                return null; // Handle the exception appropriately for your application
            }
        }
        return null;
    }

    private void visualize(TETile[][] world) {
        TER.drawTiles(world);
    }

    // User Interface
    private static void menuPage() {
        setXscale(0, 80);
        setYscale(0, 64);
        clear(Color.BLACK);
        setPenRadius(0.05);
        setPenColor(Color.WHITE);
        setFont();
        Font font1 = new Font("Monospaced", Font.PLAIN, 40);
        setFont(font1);
        text(40, 50, "CS61B: THE GAME");
        Font font2 = new Font("Monospaced", Font.PLAIN, 25);
        setFont(font2);
        text(40, 45, "presented by Dudu & Yiqing");
        Font fontSmall = new Font("Monospaced", Font.PLAIN, 30);
        setFont(fontSmall);
        text(40, 30, "New Game(N)");
        text(40, 27, "Load Game(L)");
        text(40, 24, "Quit(Q)");
        show();
    }

    public static void seedPage() {
        setXscale(0, 80);
        setYscale(0, 48);
        clear(Color.BLACK);
        setPenRadius(0.05);
        setPenColor(Color.WHITE);
        setFont();
        Font fontLarge = new Font("Monospaced", Font.PLAIN, 30);
        setFont(fontLarge);
        textLeft(15, 35, "Please Type Your Seed Here:");
        Font font2 = new Font("Monospaced", Font.PLAIN, 25);
        setFont(font2);
        textLeft(15, 28, "Ready to Start New Game? Press S");
        textLeft(15, 24, "Restart Everything? Press R");
        show();
    }

    private static void errorPage() {
        setXscale(0, 80);
        setYscale(0, 48);
        clear(Color.BLACK);
        setPenRadius(0.05);
        setPenColor(Color.WHITE);
        setFont();
        Font fontLarge = new Font("Monospaced", Font.PLAIN, 30);
        setFont(fontLarge);
        text(40, 40, "Error Input! Please Try Again");
        StdDraw.show();
        StdDraw.pause(2000);
    }

    private static void instructionPage() {
        setXscale(0, 80);
        setYscale(0, 48);
        clear(Color.BLACK);
        setPenRadius(0.05);
        setPenColor(Color.WHITE);
        setFont();
        Font fontLarge = new Font("Monospaced", Font.PLAIN, 30);
        setFont(fontLarge);
        textLeft(15, 33, "Discard Your Current Game and Restart: Press R");
        textLeft(15, 30, "Save Your Current Game and Restart: Press :R");
        textLeft(15, 27, "Save and Exit: Press :Q");
        textLeft(15, 24, "Move Up: Press W");
        textLeft(15, 21, "Move Down: Press S");
        textLeft(15, 18, "Move Left: Press A");
        textLeft(15, 15, "Move Right: Press D");
        StdDraw.show();
        StdDraw.pause(4000);
    }
}
