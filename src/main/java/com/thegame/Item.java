package main.java.com.thegame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Item {
    private String  name;
    private String	description;
    private final Map<Item, Item> recipes = new HashMap<>();

    public void Item(String name) {
        this.name = name;
        this.description = "";
    }

    public void Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void Item(String name, String description, Boolean usable) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addRecipe(Item obj, Item result) {
        recipes.put(obj, result);
    }

    public static void  showItems(LinkedList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ": " + items.get(i).getName());
        }
    }
}
