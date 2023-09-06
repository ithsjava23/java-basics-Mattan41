package org.example;


import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String choice = "";
        int[][] electricityPrice = new int[24][2];


        while (!choice.equalsIgnoreCase("e")){
            printMenu();
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> inputElectricityPrice(electricityPrice, sc);
                case "2" -> {
                    maxValue(electricityPrice);
                    minValue(electricityPrice);
                    averageValue(electricityPrice);
                }

                case "3" -> sortElectricityPrices();
                case "4" -> cheapestTimeToCharge();
                case "5" -> visualizeElectricityPrices();
            }
        }



        /*
        int max = electricityPrice[0][2];
        //int min = electricityPrice[0][0];
        //int minPlace = min;

        for (int i = 0; i < electricityPrice.length; i++) {
            if (electricityPrice[i][2] > max) {
                max = electricityPrice[i][2];
            }
        }
       int maxPlace = max;
       for (int i = 0; i < electricityPrice.length; i++) {
           if (electricityPrice[i][2] == maxPlace)
               System.out.println("Högsta priset " + max + " öre är kl " + i + "-" + (i+1));
       }

 */


        /* testa att array lagrar värden

        for (int i = 0; i < electricityPrice.length; i++) {
            for (int j = 0; j < electricityPrice[i].length; j++) {
            System.out.print(electricityPrice[i][j] + " ");
        }
        System.out.println();
    }

         */
            //System.out.println(Arrays.toString(electricityPrice));
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
                maxRow = i;
            }
        }
        String price = String.format("%02d", maxRow) + "-" + String.format("%02d", maxRow+1);
        System.out.print("Högsta pris: " + price + ", " + max + " öre/kWh\n");
    }
    //ToDo: create multiple responses if multiple max's
    public static void minValue(int[][] electricityPrice) {
        int min = Integer.MAX_VALUE;
        int minRow = -1;
        for (int i = 0; i < electricityPrice.length; i++) {
            if (electricityPrice[i][1] < min) {
                min = electricityPrice[i][1];
                minRow = i;
            }
        }
        String price = String.format("%02d", minRow) + "-" + String.format("%02d", minRow+1);
        System.out.print("Lägsta pris: " + price + ", " + min + " öre/kWh\n");
    }
    //ToDo: create multiple responses if multiple lows
    public static void averageValue(int[][] electricityPrice) {
        double sum = 0;
        for (int i = 0; i < electricityPrice.length; i++) {
            sum += electricityPrice[i][1];
        }
        double average = sum / electricityPrice.length;
        System.out.print("Medelpris: "+ average + " öre/kWh\n \n");
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