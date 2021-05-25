package main.java.com.thegame;

import java.util.LinkedList;

public class Grid {
    protected Terrain terrain;
    private LinkedList<Item> items = new LinkedList<>();

    public  Grid () {
        this.terrain = Terrain.SNOW;
    }

    public  Grid (Terrain type) {
        this.terrain = type;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public LinkedList<Item> getItems() {
        return items;
    }
}
