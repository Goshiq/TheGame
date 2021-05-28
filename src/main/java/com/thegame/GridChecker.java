package main.java.com.thegame;

public class GridChecker extends Thread{
    private Player player;
    private static Terrain terrain;
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

    public int  getHp() {
        return hp;
    }

    public static void checkGrid(Player player, Terrain terr) {
        terrain = terr;
        if (terrain == Terrain.LAVA) {
            System.out.println("Жарковато тут...");
            if (player.getInventory().contains("Сапоги")) {
                System.out.println("Хорошо, что я в сапогах");
            }
            else {
                System.out.println("Ты йог что ли, что ходишь по огню босиком?");
                GridChecker dieProcess = new GridChecker(player);
                Thread t1 = new Thread(dieProcess);
                t1.start();
            }
        }
    }

    @Override
    public void run() {
       try {
           while (true) {
               Thread.sleep(3000);
               if (terrain != Terrain.LAVA) {
                   break;
               }
               hp -= downGrade;
               player.setHealth(hp);
               if (hp <= 0) {
                   System.out.println("Центр, у нас потери, присылайте вертолёт!");
                   System.exit(1);
               }
               System.out.println("\nЕщё немного и человек будет готов!\nЗдоровье: " + Integer.toString(hp) + "/100");
               System.out.print("Введите действие: ");
           }
       }
       catch (InterruptedException e) {
           Thread.currentThread().interrupt();
       }
    }
}
