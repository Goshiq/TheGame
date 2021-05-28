package main.java.com.thegame;

import java.util.*;

public class Item {
    private String name;
    private String description;
    public boolean aLot = false;
    private static Map<Map<String, String>, String> recipes = new HashMap<>();
    private static LinkedList<Item> itemList = new LinkedList<>();
    private Item item;

    public Item(String name) {
        this.name = name;
        this.description = "";
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(String name, String description, boolean aLot) {
        this.name = name;
        this.description = description;
        this.aLot = aLot;
    }

    public Item(Item toAdd) {
        name = toAdd.name;
        description = toAdd.description;
        aLot = toAdd.aLot;
    }

    public static boolean getALot(String str) {
        Item item;
        item = itemList.stream().filter(el -> el.getName().compareTo(str) == 0).findFirst().orElse(null);
        if (item != null)
            return item.aLot;
        return false;
    }

    public String getName() {
        return name;
    }

    private String getDescription() {
        return description;
    }

    public static String findDescription(String str) {
        Item item;
        item = itemList.stream().filter(el -> el.getName().compareTo(str) == 0).findFirst().orElse(null);
        if (item != null)
            return item.getDescription();
        return null;
    }

    public static void addRecipe(String item1, String item2, Item item3) {
        Map<String, String> toAdd = new HashMap<>();
        Map<String, String> toAdd2 = new HashMap<>();

        toAdd.put(item1, item2);
        recipes.put(toAdd, item3.getName());
        toAdd2.put(item2, item1);
        recipes.put(toAdd2, item3.getName());
    }

    public static void showItems(LinkedList<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ": " + items.get(i));
        }
    }

    public static void addItem(Item item) {
        itemList.add(item);
    }

    public static LinkedList<String> getPairs(String str, LinkedList<String> inventory) {
        LinkedList<String> answer = new LinkedList<>();
        int count = 0;

        for (String item : inventory) {
            if (item.equals(str))
                continue;
            String toAdd = Item.checkItem(str, item);
            if (toAdd != null) {
                count++;
                answer.add(item);
            }
        }
        if (count == 0) {
            answer = null;
        }
        return answer;
    }

    public static String getResult(String item, String item2) {
        Map<String, String> result;
        result = new HashMap<>();
        result.put(item, item2);
        System.out.println("Опа, получился " + recipes.get(result));
        return recipes.get(result);
    }

    private static String checkItem(String item1, String item2) {
        Map<String, String> key = new HashMap<>();
        key.put(item1, item2);
        return recipes.get(key);
    }

    public boolean getALot() {
        return aLot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }
}
