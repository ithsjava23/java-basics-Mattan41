package org.example;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String choice = "";
        int[][] electricityPrice = new int[24][2];

        while (!choice.equalsIgnoreCase("e")) {
            printMenu();
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> inputElectricityPrice(electricityPrice, sc);
                case "2" -> {
                    maxValue(electricityPrice);
                    minValue(electricityPrice);
                    averageValue(electricityPrice);
                }

                case "3" -> sortElectricityPrices(electricityPrice);
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

    public static void inputElectricityPrice(int[][] electricityPrice, Scanner sc) {
        for (int i = 0; i < electricityPrice.length; i++) {
            System.out.println("Ange elpris i öre för timme " + i + "-" + (i + 1) + ":");
            electricityPrice[i][1] = Integer.parseInt(sc.nextLine());
            electricityPrice[i][0] = (i);
//ToDo: add method for no input
        }
    }

    public static void maxValue(int[][] electricityPrice) {
        int max = Integer.MIN_VALUE;
        int maxRow = -1;
        for (int i = 0; i < electricityPrice.length; i++) {
            if (electricityPrice[i][1] > max) {
                max = electricityPrice[i][1];
            }
        }
        for (int i = 0; i < electricityPrice.length; i++) {
            if (electricityPrice[i][1] == max) {
                maxRow = i;
                String time = String.format("%02d", maxRow) + "-" + String.format("%02d", maxRow + 1);
                System.out.print("Högsta pris: " + time + ", " + max + " öre/kWh\n");
            }
        }

    }
    public static void minValue(int[][] electricityPrice) {
        int min = Integer.MAX_VALUE;
        int minRow = -1;
        for (int i = 0; i < electricityPrice.length; i++) {
            if (electricityPrice[i][1] < min) {
                min = electricityPrice[i][1];
                ;
            }
        }
        for (int i = 0; i < electricityPrice.length; i++) {
            if (electricityPrice[i][1] == min) {
                minRow = i;
                String time = String.format("%02d", minRow) + "-" + String.format("%02d", minRow + 1);
                System.out.print("Lägsta pris: " + time + ", " + min + " öre/kWh\n");
            }
        }
    }
    public static void averageValue(int[][] electricityPrice) {
        double sum = 0;
        for (int i = 0; i < electricityPrice.length; i++) {
            sum += electricityPrice[i][1];
        }
        double average = sum / electricityPrice.length;
        System.out.print("Medelpris: "+ average + " öre/kWh\n \n");
    }

    public static void sortElectricityPrices(int[][] electricityPrice) {
        for (int i = 0; i < electricityPrice.length; i++) {
            for (int j = i + 1; j < electricityPrice.length; j++) {
                if (electricityPrice[i][1] > electricityPrice[j][1]) {
                    int[] temp = electricityPrice[i];
                    electricityPrice[i] = electricityPrice[j];
                    electricityPrice[j] = temp;
                }
            }
        }
        for (int i = 0; i < electricityPrice.length; i++) {
            String time = String.format("%02d", electricityPrice[i][0]) + "-" + String.format("%02d", electricityPrice[i][0]+1);
            System.out.println(time + " " + electricityPrice[i][1] + " öre");
        }
    }
        //Arrays.sort(electricityPrice, Comparator.comparingInt(a -> a[1])); // sort array method

    public static void cheapestTimeToCharge(){
        //ToDo: best charge time method
    }
    public static void visualizeElectricityPrices(){
        //ToDo: Visualisation method + add menu choice
    }


}