package com.thegame;

import java.util.TreeMap;

public class GridChecker extends Thread{
    private Player player;
    private static Terrain terrain;
    private static volatile boolean started = false;
    private static Terrain lastTerrain = Terrain.GRASS;
    private int hp;
    private int downGrade;

    public GridChecker(Player player) {
        this.hp = player.getHealth();
        downGrade = 10;
        this.player = player;
    }

    public GridChecker(Player player, int loseHp) {
        this.hp = player.getHealth();
        downGrade = loseHp;
    }

    public static void checkGrid(Player player, Terrain terr) {
        terrain = terr;
        if (started)
            return;
        if (terrain == Terrain.LAVA) {
            started = true;
            System.out.println("Жарковато тут...");
            if (player.getInventory().contains("Сапоги")) {
                System.out.println("Хорошо, что ты в сапогах");
            }
            else {
                System.out.println("Ты йог что ли, что ходишь по огню босиком?");
                GridChecker dieProcess = new GridChecker(player);
                Thread t1 = new Thread(dieProcess);
                t1.start();
            }
        }
        else if (terrain == Terrain.SNOW) {
            started = true;
            System.out.println("А тут свежо, сейчас бы шуба не помешала...");
            GridChecker dieProcess = new GridChecker(player);
            Thread t1 = new Thread(dieProcess);
            t1.start();
        }
    }

    @Override
    public void run() {
       try {
           while (true) {
               Thread.sleep(3000);
               if (terrain != Terrain.LAVA && terrain != Terrain.SNOW) {
                   started = false;
                   break;
               }
               hp -= downGrade;
               player.setHealth(hp);
               if (hp <= 0) {
                   System.out.println("Центр, у нас потери, высылайте вертолёт!");
                   System.exit(1);
               }
               if (terrain == Terrain.LAVA)
                   System.out.println("\nЕщё немного и человек будет готов!\nЗдоровье: " + Integer.toString(hp) + "/100");
               else if (terrain == Terrain.SNOW)
                   System.out.println("\nХолодно! Кому ты потом такой отморозок нужен будешь?\nЗдоровье: " + Integer.toString(hp) + "/100");
               System.out.print("Введите действие: ");
           }
       }
       catch (InterruptedException e) {
           Thread.currentThread().interrupt();
       }
    }
}
