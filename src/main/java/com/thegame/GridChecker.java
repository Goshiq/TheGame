package main.java.com.thegame;

public class GridChecker extends Thread{
    private int hp;
    private int downGrade;
    private static volatile boolean exitTread;

    public GridChecker(Player player) {
        this.hp = player.getHealth();
        downGrade = 10;
        exitTread = false;
    }

    public GridChecker(Player player, int loseHp) {
        this.hp = player.getHealth();
        downGrade = loseHp;
        exitTread = false;
    }

    public GridChecker(Player player, int loseHp, boolean ex) {
        this.hp = player.getHealth();
        downGrade = loseHp;
        exitTread = ex;
    }

    public int  getHp() {
        return hp;
    }

    public static void checkGrid(Player player, Terrain terrain) {
        if (terrain == Terrain.LAVA) {
            System.out.println("Жарковато тут...");
            if (player.getInventory().contains("Сапоги")) {
                System.out.println("Хорошо, что я в сапогах");
            }
            else {
                System.out.println("Ты йог что ли, что ходишь по огню босиком?");
                GridChecker dieProcess = new GridChecker(player);
                Thread t1 = new Thread(dieProcess);
                exitTread = true;
                t1.start();
            }
        }
        else
            exitTread = true;
    }

    @Override
    public void run() {
       try {
           while (exitTread) {
               Thread.sleep(3000);
               hp -= downGrade;
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
