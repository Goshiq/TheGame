package main.java.com.thegame;

import java.util.LinkedList;
import java.util.stream.Stream;

public class MyMap {
    private final Grid[][] worldMap;

    public  MyMap () {
        int x = 10;
        int y = 5;
        worldMap = new Grid[x][y];
        randomizeTerrain(this.getWidth(), this.getHeight(), 2, this);
    }

    public  MyMap (int w, int h) {
        worldMap = new Grid[w][h];
        randomizeTerrain(w, h, 2, this);
    }

    public  MyMap (int w, int h, Terrain type) {
        worldMap = new Grid[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                worldMap[i][j] = new Grid(type);
            }
        }
    }

    public int getHeight() {
        return worldMap[0].length;
    }

    public int  getWidth() {
        return worldMap.length;
    }

    public void getInfo(int x, int y) {
        LinkedList<Item> items = worldMap[x][y].getItems();
        Terrain terrain = worldMap[x][y].terrain;

        System.out.println("Покрытие: " + terrain.getName());
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) {
                System.out.println("А ещё у нас тут есть:");
            }
            System.out.println(i + ": " + items.get(i).getName());
        }
    }

    public  Terrain getTerrain (int i, int j) throws Exception {
        if (i == 0 || j == 0 || i > worldMap.length || j > worldMap[i - 1].length) {
            throw new Exception("Index is out of range");
        }
        return worldMap[i - 1][j - 1].terrain;
    }

    public void setTerrain (int i, int j, Terrain type) throws Exception {
        if (i == 0 || j == 0 || i > worldMap.length || j > worldMap[i - 1].length) {
            throw new Exception("Index is out of range");
        }
        worldMap[i - 1][j - 1].terrain = type;
    }

    public void printMap () {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                try {
                    System.out.printf("%-4s ", this.getTerrain(j + 1, i + 1).getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    private void randomizeTerrain(int x, int y, int size, MyMap map) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map.worldMap[i][j] = new Grid(Terrain.randomTerrain());
                if (j > 0) {
                    if (j % size > 0) {
                        map.worldMap[i][j].terrain = map.worldMap[i][j - 1].terrain;
                    }
                    else {
                        while (map.worldMap[i][j].terrain.equals(map.worldMap[i][j - 1].terrain)) {
                            map.worldMap[i][j] = new Grid(Terrain.randomTerrain());
                        }
                    }
                }
                if (i > 0) {
                    if (i % size > 0) {
                        map.worldMap[i][j].terrain = map.worldMap[i - 1][j].terrain;
                    }
                    else {
                        while (map.worldMap[i][j].terrain.equals(map.worldMap[i - 1][j].terrain)) {
                            map.worldMap[i][j] = new Grid(Terrain.randomTerrain());
                        }
                    }
                }
            }
        }
    }
}
