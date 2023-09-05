package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("e")){
            printMenu();
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> inputElectricityPrice();
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

    public static void inputElectricityPrice() {
    //ToDo: input method
    }
    public static void minMaxAverageValue(){
    //ToDo: minMaxMethod method
    }
    public static void sortElectricityPrices(){
        //ToDo: sorting Method
    }
    public static void cheapestTimeToCharge(){
        //ToDo: charge time method
    }

    public static void visualizeElectricityPrices(){
        //ToDo: Visualisation method
    }

}