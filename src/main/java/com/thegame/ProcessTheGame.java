package com.thegame;

import java.util.LinkedList;
import java.util.Scanner;

public class ProcessTheGame {
    static LinkedList<String> pairs;
    private static volatile boolean exitTread = false;

    public static void start(MyMap map) {
        Scanner scanner = new Scanner(System.in);
        String str;
        System.out.println("Как звать-то нашего героя? ");
        str = scanner.next();
        Player player = new Player(str);
        tellTheStory(str);
        try {
            doAction(str, map, player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tellTheStory(String str) {
        System.out.println("Ну, здравствуй, " + str);
        System.out.println("Очнулся ты среди поля, как тут оказался- одним божьим коровкам известно");
        System.out.println("Нужно что-то делать... Но большой вопрос: Что? И не менее большой: Зачем?");
        showHelp();
    }

    private static void doAction(String str, MyMap map, Player player) throws Exception {
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
                        System.exit(0);
                        return;
                    }
                    case ("w") -> {
                        System.out.println("Идём на север");
                        player.moveUp(map.getHeight());
                        GridChecker.checkGrid(player, map.getTerrain(player.getX(), player.getY()));
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("a") -> {
                        System.out.println("Идём на запад");
                        player.moveLeft(map.getWidth());
                        GridChecker.checkGrid(player, map.getTerrain(player.getX(), player.getY()));
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("s") -> {
                        System.out.println("Идём на юг");
                        player.moveDown(map.getHeight());
                        GridChecker.checkGrid(player, map.getTerrain(player.getX(), player.getY()));
                        player.setDialogStatement(DialogStatement.MAIN);
                    }
                    case ("d") -> {
                        System.out.println("Идём на восток");
                        player.moveRight(map.getWidth());
                        GridChecker.checkGrid(player, map.getTerrain(player.getX(), player.getY()));
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
            System.out.println();
        }
    }



    private static void checkAction(int i, Player player, MyMap map) throws Exception {
        LinkedList<String>    items = map.getItems(player.getX(), player.getY());

        if (player.getDialogStatement() == DialogStatement.LOOKAROUND) {
            if (i > items.size()) {
                System.out.println("Ты откуда это взял?..");
            } else {
                System.out.print(items.get(i - 1) + ": ");
                System.out.println(Item.findDescription(items.get(i - 1)));
                if (map.getTerrain(player.getX(), player.getY()) == Terrain.LAVA) {
                    System.out.println("1: Использовать");
                }
                else
                    System.out.println("1: Взять с собой");
                System.out.println("Любая клавиша: Отмена");
                System.out.print("Введите действие: ");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                switch (str.toLowerCase()) {
                    case ("1") -> {
                        if (map.getTerrain(player.getX(), player.getY()) == Terrain.LAVA) {
                            if (player.getInventory().contains("Кружка со снегом")) {
                                player.getInventory().remove("Кружка со снегом");
                                player.addItem("Кружка с кипятком");
                            }
                            else if (player.getInventory().contains("Кастрюля снега")) {
                                player.getInventory().remove("Кастрюля снега");
                                player.addItem("Кастрюля с кипящей водой");
                            }
                            else if (player.getInventory().contains("Кружка воды")) {
                                player.getInventory().remove("Кружка воды");
                                player.addItem("Кружка с кипятком");
                            }
                            else if (player.getInventory().contains("Кастрюля воды")) {
                                player.getInventory().remove("Кастрюля воды");
                                player.addItem("Кастрюля с кипящей водой");
                            }
                            else
                                System.out.println("Тепло");
                        }
                        else if (map.getTerrain(player.getX(), player.getY()) == Terrain.SNOW) {
                            if (player.getInventory().contains("Кружка")) {
                                player.getInventory().remove("Кружка");
                                player.addItem("Кружка со снегом");
                            }
                            else if (player.getInventory().contains("Кастрюлька")) {
                                player.getInventory().remove("Кастрюлька");
                                player.addItem("Кастрюля снега");
                            }
                            else
                                System.out.println("Ты его в ладошках понесёшь?");
                        }
                        else if (map.getTerrain(player.getX(), player.getY()) == Terrain.WATER && items.get(i - 1).equals("Вода")) {
                            if (player.getInventory().contains("Кружка")) {
                                player.getInventory().remove("Кружка");
                                player.addItem("Кружка воды");
                            }
                            else if (player.getInventory().contains("Кастрюлька")) {
                                player.getInventory().remove("Кастрюлька");
                                player.addItem("Кастрюля воды");
                            }
                            else
                                System.out.println("Ты её в ладошках понесёшь?");
                        }
                        else {
                            player.addItem(items.get(i - 1));
                            if (!Item.getALot(items.get(i - 1)))
                                items.remove(i - 1);
                        }
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
                    System.out.println(Item.findDescription(player.getInventory().get(i - 1)));
                }
                case ("2") -> {
                    System.out.println("Как гора с плеч!");
                    items.add(player.getInventory().get(i - 1));
                    player.getInventory().remove(i - 1);
                }
                case ("3") -> {
                    if (player.getCurrentItem().equals("Рыбный суп")) {
                        System.out.println("Эх, сейчас бы ещё огурчиков!\nЗдоровье восстановлено");
                        player.setHealth(100);
                        return;
                    }
                    pairs = Item.getPairs(player.getInventory().get(i - 1), player.getInventory());
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
            String  result = Item.getResult(player.getCurrentItem(), pairs.get(i - 1));
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
        System.out.println("Здоровье: "+ player.getCurrentHealth());
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