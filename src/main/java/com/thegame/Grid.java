package main.java.com.thegame;

public class Grid {
    protected Terrain terrain;

    public  Grid () {
        this.terrain = Terrain.SNOW;
    }

    public  Grid (Terrain type) {
        this.terrain = type;
    }
}
