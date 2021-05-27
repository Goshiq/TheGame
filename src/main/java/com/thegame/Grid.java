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
        if (items.stream().filter(el -> el.getName().compareTo(item.getName()) == 0).findAny().isEmpty()) {
            this.items.add(item);
        }
    }

    public LinkedList<Item> getItems() {
        switch (terrain) {
            //cast GRASS -> this.addItem(new Item("Травка", "Не та, о которой ты подумал", true));
            case LAVA -> this.addItem(new Item("Огонь", "Пахнет жареным", true));
            case SAND -> this.addItem(new Item("Песок", "Видимо, бабуля здесь часто бывала", true));
            case SNOW -> this.addItem(new Item("Снег", "Не жёлтый", true));
            case STONE -> this.addItem(new Item("Камень", "Похож на камень", true));
            case WATER -> this.addItem(new Item("Вода", "Мокрая", true));
        }
        return items;
    }
}
