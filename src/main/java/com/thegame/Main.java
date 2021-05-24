package main.java.com.thegame;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            MyMap   map = new MyMap();
            ProcessTheGame.start(map);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
