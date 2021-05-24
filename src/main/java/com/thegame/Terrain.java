package main.java.com.thegame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Terrain {
    GRASS,
    GROUND,
    LAVA,
    SAND,
    SNOW,
    STONE,
    WATER;

    private static final List<Terrain> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Terrain randomTerrain()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
