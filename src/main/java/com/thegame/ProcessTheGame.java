package main.java.com.thegame;

import java.util.Locale;
import java.util.Scanner;

public class ProcessTheGame {
    public static void start(MyMap map) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while (true) {
            System.out.print("Введите действие: ");
            str = scanner.next();
            switch (str.toLowerCase()) {
                case ("exit"):
                    System.out.println("До свидания!");
                    return;
                case ("w"):
                    System.out.println("Идём на север");
                    break;
                case ("a"):
                    System.out.println("Идём на запад");
                    break ;
                case ("s"):
                    System.out.println("Идём на юг");
                    break;
                case ("d"):
                    System.out.println("Идём на восток");
                    break ;
                case ("h"):
                    showHelp();
                    break ;
                case ("i"):
                    showInfo();
                    break ;
                case ("m"):
                    System.out.println("Таааак, попробуем разобраться в карте...");
                    map.printMap();
                    break ;
                default:
                    System.out.println("Что-то непонятное...");
                    break;
            }
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

    private static void showInfo() {
        System.out.println("Посмоооотрим, что у нас есть:");
    }

    public static void end() {
        System.out.println("End");
    }

}