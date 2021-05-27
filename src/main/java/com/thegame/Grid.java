package main.java.com.thegame;

import java.util.LinkedList;

public class Grid {
    protected Terrain terrain;
    private LinkedList<String> items = new LinkedList<>();

    public  Grid () {
        this.terrain = Terrain.SNOW;
    }

    public  Grid (Terrain type) {
        this.terrain = type;
    }

    public void addItem(String item) {
        if (items.stream().filter(el -> el.compareTo(item) == 0).findAny().isEmpty()) {
            this.items.add(item);
        }
    }

    public LinkedList<String> getItems() {
        switch (terrain) {
            //cast GRASS -> this.addItem(new Item("Травка", "Не та, о которой ты подумал", true));
            case LAVA -> this.addItem("Огонь");
            case SAND -> this.addItem("Песок");
            case SNOW -> this.addItem("Снег");
            case STONE -> this.addItem("Камень");
            case WATER -> this.addItem("Вода");
        }
        return items;
    }
}
