package main.java.com.thegame;

import java.util.LinkedList;
import java.util.Scanner;

public class ProcessTheGame {
    static LinkedList<Item> pairs;

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
        System.out.println("Очнулся ты среди поля, как тут оказался- одним божьим коровкам известно");
        System.out.println("Нужно что-то делать... Но большой вопрос: Что? И не менее большой: Зачем?");
        showHelp();
    }

    private static void doAction(String str, MyMap map, Player player) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите действие: ");
            str = scanner.next();
            if (player.getDialogStatement() != DialogStatement.MAIN && str.length() == 1 && (str.charAt(0) >= '0' && str.charAt(0) <= '9')) {
                checkAction(str.charAt(0) - 48, player, map);
            } else {
                switch (str.toLowerCase()) {
                    case ("exit") -> {
                        System.out.println("До свидания!");
                        return;
                    }
                    case ("w") -> {
                        System.out.println("Идём на север");
                        player.moveUp(map.getHeight());
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("a") -> {
                        System.out.println("Идём на запад");
                        player.moveLeft(map.getWidth());
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("s") -> {
                        System.out.println("Идём на юг");
                        player.moveDown(map.getHeight());
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("d") -> {
                        System.out.println("Идём на восток");
                        player.moveRight(map.getWidth());
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("?") -> {
                        player.setDialogStatement(DialogStatement.LOOKAROUND);
                        lookAround(map, player);
                    }
                    case ("help"), ("h") -> {
                        showHelp();
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("i") -> showInfo(player);
                    case ("m") -> {
                        System.out.println("Ага-а-а, а где же я на карте...");
                        map.printMap();
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    default -> {
                        System.out.println("Что-то непонятное...");
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                }
            }
        }
    }

    private static void checkAction(int i, Player player, MyMap map) {
        LinkedList<Item>    items = map.getItems(player.getX(), player.getY());

        if (player.getDialogStatement() == DialogStatement.LOOKAROUND) {
            if (i > items.size()) {
                System.out.println("Ты откуда это взял?..");
            } else {
                System.out.print(items.get(i - 1).getName() + ": ");
                System.out.println(items.get(i - 1).getDescription());
                System.out.println("1: Взять с собой");
                System.out.println("Любая клавиша: Отмена");
                System.out.print("Введите действие: ");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                switch (str.toLowerCase()) {
                    case ("1") -> {
                        player.addItem(items.get(i - 1));
                        if (!items.get(i - 1).getALot())
                            items.remove(i - 1);
                    }
                    case ("exit") -> System.exit(0);
                }
            }
        } else if (player.getDialogStatement() == DialogStatement.INVENTORY && i <= player.getInventory().size()) {
            player.setCurrentItem(player.getInventory().get(i - 1));
            System.out.println("1: Осмотреть");
            System.out.println("2: Выбросить");
            System.out.println("3: Использовать");
            System.out.println("Любая клавиша: Отмена");
            System.out.print("Введите действие: ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            switch (str) {
                case ("1") -> {
                    System.out.println(player.getInventory().get(i - 1).getDescription());
                }
                case ("2") -> {
                    System.out.println("Как гора с плеч!");
                    items.add(player.getInventory().get(i - 1));
                    player.getInventory().remove(i - 1);
                }
                case ("3") -> {
                    pairs = player.getInventory().get(i - 1).getPairs(player.getInventory());
                    if (pairs != null) {
                        int count = 0;
                        if (count++ == 0)
                            System.out.println("Можно использовать с: ");
                        player.setDialogStatement(DialogStatement.USING);
                        Item.showItems(pairs);
                    } else
                        System.out.println("Не с чем использовать");
                }
                case ("exit") -> System.exit(0);
            }
        } else if (player.getDialogStatement() == DialogStatement.USING) {
            Item    result = player.getCurrentItem().getResult(player.getCurrentItem(), pairs.get(i - 1));
            player.addItem(result);
            player.getInventory().remove(pairs.get(i - 1));
            player.getInventory().remove(player.getCurrentItem());
        }
        else
            System.out.println("Эм?...");
    }

    private static void showHelp() {
        System.out.println("Помощь:");
        System.out.println("Клавиши WASD для перемещения");
        System.out.println("Цифры для выбора действия (если доступны)");
        System.out.println("h: помощь (то, что видишь сейчас)");
        System.out.println("i: информация о персонаже");
        System.out.println("m: карта");
        System.out.println("?: осмотреться вокруг");
        System.out.println("exit: выход из игры");
    }

    private static void showInfo(Player player) {
        player.setDialogStatement(DialogStatement.INVENTORY);
        System.out.println("Мамкин бродяга: " + player.getName());
        if (player.getInventory().size() == 0) {
            System.out.println("Гол как сокол");
        } else {
            if (player.getInventory().size() > 0) {
                System.out.println("Посмоооотрим, что у нас есть:");
                Item.showItems(player.getInventory());
            }
        }
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
            } else {
                System.out.println(map.getTerrain(x, y - 1).getName());
            }
            System.out.print("На запад: ");
            if (x == 0) {
                System.out.println(map.getTerrain(map.getWidth() - 1, y).getName());
            } else {
                System.out.println(map.getTerrain(x - 1, y).getName());
            }
            System.out.print("На восток: ");
            if (x == map.getWidth() - 1) {
                System.out.println(map.getTerrain(0, y).getName());
            } else {
                System.out.println(map.getTerrain(x + 1, y).getName());
            }
            System.out.print("На юг: ");
            if (y == map.getHeight() - 1) {
                System.out.println(map.getTerrain(x, 0).getName());
            } else {
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