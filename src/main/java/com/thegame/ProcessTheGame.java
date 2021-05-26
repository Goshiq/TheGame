package main.java.com.thegame;

import java.util.LinkedList;
import java.util.Scanner;

public class ProcessTheGame {
    public static void start(MyMap map) {
        Scanner scanner = new Scanner(System.in);
        String str;
        System.out.println("Как звать-то нашего героя? ");
        str = scanner.next();
        Player player = new Player(str);
        tellTheStory(str);
        doAction(str, map, player);
    }

    private static void tellTheStory(String str) {
        System.out.println("Ну, здравствуй, " + str);
    }

    private static void doAction(String str, MyMap map, Player player) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите действие: ");
            str = scanner.next();
            if (str.length() == 1 && (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
                checkAction(str.charAt(0) + 48, map.getItems(player.getX(), player.getY()));
            }
            switch (str.toLowerCase()) {
                case ("exit"):
                    System.out.println("До свидания!");
                    return;
                case ("w"):
                    System.out.println("Идём на север");
                    player.moveUp(map.getHeight());
                    break;
                case ("a"):
                    System.out.println("Идём на запад");
                    player.moveLeft(map.getWidth());
                    break;
                case ("s"):
                    System.out.println("Идём на юг");
                    player.moveDown(map.getHeight());
                    break;
                case ("d"):
                    System.out.println("Идём на восток");
                    player.moveRight(map.getWidth());
                    break;
                case ("?"):
                    lookAround(map, player);
                    break;
                case ("help"):
                case ("h"):
                    showHelp();
                    break;
                case ("i"):
                    showInfo(player);
                    break;
                case ("m"):
                    System.out.println("Ага-а-а, а где же я на карте...");
                    map.printMap();
                    break;
                default:
                    System.out.println("Что-то непонятное...");
                    break;
            }
        }
    }

    private static void checkAction(int i, LinkedList<Item> items) {
        if (i > items.size()) {
            System.out.println("Что-то непонятное...");
        }
        else {
            items.get(i).getDescription();
        }
    }

    private static void showHelp() {
        System.out.println("Помощь:");
        System.out.println("Клавиши WASD для перемещения");
        System.out.println("Цифры для выбора действия (если доступны)");
        System.out.println("h: помощь (то, что видишь сейчас)");
        System.out.println("i: информация о персонаже");
        System.out.println("m: карта");
        System.out.println("exit: выход из игры");
    }

    private static void showInfo(Player player) {
        System.out.println("Мамкин бродяга: " + player.getName());
        System.out.println("Посмоооотрим, что у нас есть:");
    }

    private static void lookAround(MyMap map, Player player) {
        int x, y;
        try {
            x = player.getX();
            y = player.getY();
            map.getInfo(x, y);
            System.out.print("На север: ");
            if (y == 0) {
                System.out.println(map.getTerrain(x, map.getHeight() - 1).getName());
            }
            else {
                System.out.println(map.getTerrain(x, y - 1).getName());
            }
            System.out.print("На запад: ");
            if (x == 0) {
                System.out.println(map.getTerrain(map.getWidth() - 1, y).getName());
            }
            else {
                System.out.println(map.getTerrain(x - 1, y).getName());
            }
            System.out.print("На восток: ");
            if (x == map.getWidth() - 1) {
                System.out.println(map.getTerrain(0, y).getName());
            }
            else {
                System.out.println(map.getTerrain(x + 1, y).getName());
            }
            System.out.print("На юг: ");
            if (y == map.getHeight() - 1) {
                System.out.println(map.getTerrain(x, 0).getName());
            }
            else {
                System.out.println(map.getTerrain(x, y + 1).getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void end() {
        System.out.println("End");
    }

}