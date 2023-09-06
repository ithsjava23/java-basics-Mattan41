package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String choice = "";
        int[] electricityPrice = new int[24];

        while (!choice.equalsIgnoreCase("e")){
            printMenu();
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> inputElectricityPrice(electricityPrice, sc);
                case "2" -> minMaxAverageValue();
                case "3" -> sortElectricityPrices();
                case "4" -> cheapestTimeToCharge();
                case "5" -> visualizeElectricityPrices();
        }

        }

    }

    public static void printMenu() {
        System.out.print("""
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """);
    }

    private static void inputElectricityPrice(int[] electricityPrice, Scanner sc) {
        for (int i = 0; i < electricityPrice.length; i++) {
            System.out.println("Ange elpris i öre för timme " + i + "-" + (i + 1) + ":");
            electricityPrice[i] = sc.nextInt();
        }
    }

    public static void minMaxAverageValue(){
    //ToDo: minMaxMethod method
    }
    public static void sortElectricityPrices(){
        //ToDo: sorting Method + print
    }
    public static void cheapestTimeToCharge(){
        //ToDo: charge time method
    }

    public static void visualizeElectricityPrices(){
        //ToDo: Visualisation method + add menu choice
    }

}