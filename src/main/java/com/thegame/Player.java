package main.java.com.thegame;

import java.util.LinkedList;

public class Player {
    private String  name = "";
    private int x = 0;
    private int y = 0;
    private int health = 100;
    private double  capacity = 50D;
    private LinkedList<Item> inventory = new LinkedList<>();

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

    public LinkedList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(LinkedList<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
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
}
