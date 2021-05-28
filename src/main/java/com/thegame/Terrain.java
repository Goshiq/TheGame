package com.thegame;

import java.util.List;
import java.util.Random;

public enum Terrain {
    GRASS ("\uD83C\uDF40"),
    //GROUND ("Земля"),
    LAVA ("\uD83D\uDD25"),
    SAND ("\uD83C\uDFDC"),
    SNOW ("\u2744"),
    STONE ("\uD83D\uDDFF"),
    WATER ("\uD83C\uDF0A");

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
