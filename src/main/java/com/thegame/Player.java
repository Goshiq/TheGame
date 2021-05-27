package main.java.com.thegame;

import java.util.LinkedList;

public class Player {
    private String  name = "";
    private int x = 0;
    private int y = 0;
    private int health = 100;
    private double  capacity = 50D;
    private DialogStatement dialogStatement = DialogStatement.MAIN;
    private LinkedList<String> inventory = new LinkedList<>();
    private String currentItem = null;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public LinkedList<String> getInventory() {
        return inventory;
    }

    public void setInventory(LinkedList<String> inventory) {
        this.inventory = inventory;
    }

    public void addItem(String item) {
        if (inventory.stream().filter(el -> el.compareTo(item) == 0).findAny().isEmpty()) {
            inventory.add(item);
            System.out.println(item + " теперь в инвентаре. И ведь это всё на себе тащить...");
        }
        else
            System.out.println("Куда тебе ещё-то?");
    }

    public void moveUp(int mapHeight) {
        if (y == 0) {
            y = mapHeight - 1;
        }
        else
            y--;
    }

    public void moveDown (int mapHeight) {
        if (y == mapHeight - 1) {
            y = 0;
        }
        else
            y++;
    }

    public void moveRight (int mapWidth) {
        if (x == mapWidth - 1) {
            x = 0;
        }
        else
            x++;
    }

    public void moveLeft(int mapWidth) {
        if (x == 0) {
            x = mapWidth - 1;
        }
        else
            x--;
    }

    public void setDialogStatement(DialogStatement newState) {
        dialogStatement = newState;
    }

    public DialogStatement getDialogStatement() {
        return dialogStatement;
    }

    public String getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(String item) {
        currentItem = item;
    }
}
