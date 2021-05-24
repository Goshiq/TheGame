package main.java.com.thegame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Terrain {
    GRASS ("Трава"),
    GROUND ("Земля"),
    LAVA ("Лава"),
    SAND ("Песок"),
    SNOW ("Снег"),
    STONE ("Камни"),
    WATER ("Вода");

    private String name;

    Terrain (String nn) {
        this.name = nn;
    }

    private static final List<Terrain> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Terrain randomTerrain()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public String getName() {
        return this.name;
    }
}
