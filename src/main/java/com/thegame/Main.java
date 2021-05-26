package main.java.com.thegame;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            MyMap   map = new MyMap();
            initMap(map);
            ProcessTheGame.start(map);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void    initMap(MyMap map) throws Exception {
        map.setTerrain(0, 0, Terrain.GRASS);
        map.addItem(0, 0, new Item("Сапоги", "Почти как берцы, только не берцы"));
    }
}