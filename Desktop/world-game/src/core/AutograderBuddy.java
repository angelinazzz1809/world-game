package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AutograderBuddy {

    /**
     * Simulates a game, but doesn't render anything or call any StdDraw
     * methods. Instead, returns the world that would result if the input string
     * had been typed on the keyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quit and
     * save. To "quit" in this method, save the game to a file, then just return
     * the TETile[][]. Do not call System.exit(0) in this method.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] getWorldFromInput(String input) {

        boolean hasL = false;
        boolean hasN = false;
        boolean hasS = false;
        boolean hasColonQ = false;
        StringBuilder seedSB = new StringBuilder();
        List<String> move = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);

            // check if L/l appears
            if (Character.toString(curr).equalsIgnoreCase("L")) {
                hasL = true;
            }

            // check if N/n appears
            if (Character.toString(curr).equalsIgnoreCase("N")) {
                hasN = true;
            }

            // check if S/s appears
            if (Character.toString(curr).equalsIgnoreCase("S")) {
                hasS = true;
            }

            // check if :Q / :q appears
            if (Character.toString(curr).equals(":")) {
                if (i != input.length() - 1) {
                    char next = input.charAt(i + 1);
                    if (Character.toString(next).equalsIgnoreCase("Q")) {
                        hasColonQ = true;
                    }
                }
            }

            // SEED is numbers between N and S
            if (!hasL && hasN && !hasS && !hasColonQ && Character.isDigit(curr)) {
                seedSB.append(curr);
            }

            // MOVEMENT is W/A/S/D between S and colonQ
            if ((hasN && hasS) || hasL) {
                move.add(Character.toString(curr).toLowerCase());
            }
        }
        // LOAD when L appears but no N or S appears
        if (hasL && !hasN) {
            String saved = retrieve("save.txt");
            if (saved == null) {
                throw new IllegalArgumentException("Invalid Input");
            } else {
                World world = getWorldFromLoad(saved);
                TETile[][] tiles = world.getTiles();
                Integer[] avatarPos = updateAvatarFromLoad(saved, tiles);
                for (String m : move) {
                    avatarPos = movement(tiles, avatarPos, m);
                }
                if (hasColonQ) {
                    save("save.txt", getSeedFromLoad(saved), avatarPos);
                }
                return tiles;
            }
        } else if (hasN && hasS) {
            Long seed = Long.parseLong(seedSB.toString());
            World world = new World(seed);
            TETile[][] tiles = world.getTiles();
            Integer[] avatarPos = world.getAvatar();
            for (String m : move) {
                avatarPos = movement(tiles, avatarPos, m);
            }
            if (hasColonQ) {
                save("save.txt", seed, avatarPos);
            }
            return tiles;
        } else {
            throw new IllegalArgumentException("Invalid Input!");
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


    // ChatGPT Generated
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

    // ChatGPT Generated
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


    private static Integer[] movement(TETile[][] tiles, Integer[] avatarPos, String m) {
        int currX = avatarPos[0];
        int currY = avatarPos[1];

        switch (m) {
            case "d" -> {
                if (tiles[currX + 1][currY] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX + 1;
                    avatarPosNew[1] = currY;
                    tiles[currX][currY] = Tileset.FLOOR;
                    tiles[currX + 1][currY] = Tileset.AVATAR;
                    return avatarPosNew;
                }
            }
            case "a" -> {
                if (tiles[currX - 1][currY] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX - 1;
                    avatarPosNew[1] = currY;
                    tiles[currX][currY] = Tileset.FLOOR;
                    tiles[currX - 1][currY] = Tileset.AVATAR;
                    return avatarPosNew;
                }
            }
            case "w" -> {
                if (tiles[currX][currY + 1] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX;
                    avatarPosNew[1] = currY + 1;
                    tiles[currX][currY] = Tileset.FLOOR;
                    tiles[currX][currY + 1] = Tileset.AVATAR;
                    return avatarPosNew;
                }
            }
            case "s" -> {
                if (tiles[currX][currY - 1] == Tileset.FLOOR) {
                    Integer[] avatarPosNew = new Integer[2];
                    avatarPosNew[0] = currX;
                    avatarPosNew[1] = currY - 1;
                    tiles[currX][currY] = Tileset.FLOOR;
                    tiles[currX][currY - 1] = Tileset.AVATAR;
                    return avatarPosNew;
                }
            }
            default -> {
                return avatarPos;
            }
        }
        return avatarPos;
    }

    /**
     * Used to tell the autograder which tiles are the floor/ground (including
     * any lights/items resting on the ground). Change this
     * method if you add additional tiles.
     */
    public static boolean isGroundTile(TETile t) {
        return t.character() == Tileset.FLOOR.character()
                || t.character() == Tileset.AVATAR.character()
                || t.character() == Tileset.FLOWER.character();
    }

    /**
     * Used to tell the autograder while tiles are the walls/boundaries. Change
     * this method if you add additional tiles.
     */
    public static boolean isBoundaryTile(TETile t) {
        return t.character() == Tileset.WALL.character()
                || t.character() == Tileset.LOCKED_DOOR.character()
                || t.character() == Tileset.UNLOCKED_DOOR.character();
    }
}
