package main.java.com.thegame;

import java.util.Scanner;

public class ProcessTheGame {
    public static void start(MyMap map) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while (true) {
            str = scanner.next();
            switch (str) {
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
                case ("i"):
                    System.out.println("Посмоооотрим, что у нас есть:");
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

    public static void end() {
        System.out.println("End");
    }

}