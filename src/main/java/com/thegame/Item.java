package main.java.com.thegame;

import java.util.*;

public class Item {
    private String  name;
    private String	description;
    private boolean aLot = false;
    private static Map<Map<Item, Item>, Item> recipes = new HashMap<>();
    private static LinkedList<Item> itemList = new LinkedList<>();

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addRecipe(Item item1, Item item2, Item item3) {
        Map<Item, Item> toAdd = new HashMap<>();
        Map<Item, Item> toAdd2 = new HashMap<>();

        toAdd.put(item1, item2);
        recipes.put(toAdd, item3);
        toAdd2.put(item2, item1);
        recipes.put(toAdd2, item3);
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

    public LinkedList<Item> getPairs(LinkedList<Item> inventory) {
        LinkedList<Item> answer= new LinkedList<>();
        int count = 0;

        for (Item item : inventory) {
            if (item.equals(this))
                continue;
            Item toAdd = this.checkItem(item);
            if (toAdd != null) {
                count++;
                answer.add(toAdd);
            }
        }
        if (count == 0) {
            answer = null;
        }
        return answer;
    }

    public Item getResult(Item item, Item item2) {
        Map<Item, Item> result;
        result = new HashMap<>();
        result.put(item, item2);
        System.out.println("Опа, получился " + recipes.get(result).getName());
        return recipes.get(result);
    }

    private Item checkItem(Item item) {
        for (Map.Entry entry : recipes.entrySet()) {
            Map<Item, Item> keys = (Map<Item, Item>) entry.getKey();
            //if (keys.get(this) != null)
            //    return keys.get(this);
            for (Map.Entry entry1 : keys.entrySet()) {
                Item    tmp = (Item) entry1.getKey();
                if (tmp.equals(this)) {
                    return (Item) entry1.getValue();
                }
            }
        }
        return null;
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
