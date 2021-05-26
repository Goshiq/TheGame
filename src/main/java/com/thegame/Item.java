package main.java.com.thegame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Item {
    private String  name;
    private String	description;
    private final Map<Item, Item> recipes = new HashMap<>();
    private static LinkedList<Item> itemList = new LinkedList<>();

    public Item(String name) {
        this.name = name;
        this.description = "";
    }

    public Item(String name, String description) {
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
            System.out.println(i + 1 + ": " + items.get(i).getName());
        }
        if (items.size() > 0) {
            System.out.println();
        }
    }

    public static void addItem(Item item) {
        itemList.add(item);
    }

    public HashMap<String, String> useItem(LinkedList<Item> inventory) {
        Map<String, String> answer= new HashMap<>();
        int count = 0;

        for (int i = 0; i < inventory.size(); i++) {
            if (recipes.get(inventory.get(i)) != null) {
                if (count == 0) {
                    System.out.println("Можно использовать с: ");
                }
                System.out.println(count++ + 1 + " " + inventory.get(i).getName());
            };
        }
        if (count == 0) {
            System.out.println("У тебя нет ничего, с чем это можно было бы использовать...");
        }
        return null;
    }
}
