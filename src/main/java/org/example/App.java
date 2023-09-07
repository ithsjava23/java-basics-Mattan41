package org.example;


import java.util.Arrays;
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
                case "4" -> cheapestTimeToCharge(electricityPrice);
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
        int maxRow;
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
        int minRow;
        for (int i = 0; i < electricityPrice.length; i++) {
            if (electricityPrice[i][1] < min) {
                min = electricityPrice[i][1];

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
        double average = getAverageDouble(electricityPrice);
        //float getAverage(electricityPrice);
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

    public static void cheapestTimeToCharge(int[][] electricityPrice){

        int[][] cheapCharge = new int[4][2];

            int max = Integer.MAX_VALUE;
            for (int i = 0; i < cheapCharge.length; i++) {
                Arrays.fill(cheapCharge[i], max);
            }

        int[][] tempArray = new int[cheapCharge.length][cheapCharge[0].length];

            for (int i = 0; i < cheapCharge.length; i++) {
                tempArray[i] = cheapCharge[i].clone();
            }

        /*for (int row = 0; row < electricityPrice.length - 3; row++) {
            System.arraycopy(electricityPrice, row, tempArray, 0, tempArray.length);

                int sum1 = 0;
                int sum2 = 0;

                for (int i = 0; i < cheapCharge.length; i++) {
                    sum1 += cheapCharge[i][1];
                    sum2 += tempArray[i][1];
                }
                    if (sum2 < sum1) {
                        System.arraycopy(tempArray, row , cheapCharge, 0, tempArray.length);
                    }
        }

         */


        for (int row = 0; row < (electricityPrice.length - 3); row++) {
            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 4; j++) {
                    tempArray[j][0] = electricityPrice[j + row][0];
                    tempArray[j][1] = electricityPrice[j + row][1];
                }

            }
            int sum1 = 0;
            int sum2 = 0;

            for (int k = 0; k < cheapCharge.length; k++) {
                sum1 += cheapCharge[k][1];
                sum2 += tempArray[k][1];

                if (sum2 < sum1) {
                    for (int l = 0; l < 4; l++) {
                        cheapCharge[l][0] = tempArray[l][0];
                        cheapCharge[l][1] = tempArray[l][1];
                    }
                }
            }

        }



        double average = getAverageDouble(cheapCharge);
        String time = String.format("%02d", cheapCharge[0][0]);
            System.out.println("påbörja laddning: " + time + ", medelpris 4h: "+ average + " öre /kWh\n \n");



/*

        for (int i = 0; i < electricityPrice.length - 4; i++) {

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        int cheapCharge[i][j] = electricityPrice[i * 6 + j];
                    }
                }

 */


    }
    public static double getAverageDouble(int[][] electricityPrice) {
        double sum = 0;
        for (int i = 0; i < electricityPrice.length; i++) {
            sum += electricityPrice[i][1];
        }
        return sum / electricityPrice.length;
    }
    public static int sumArray(int [][] tempArray) {
        int sum = 0;

        for (int i = 0; i < tempArray.length; i++) {
            sum += tempArray[i][0];
        }
        return sum;
    }
    public static double getAverage(int[][] cheapCharge) {
        double sum = 0;
        for (int i = 0; i < cheapCharge.length; i++) {
            sum += cheapCharge[i][1];
        }
        return sum / cheapCharge.length;
    }

    public static double getAverageD(int[][] tempArray) {
        double sum = 0;
        for (int i = 0; i < tempArray.length; i++) {
            sum += tempArray[i][1];
        }
        return sum / tempArray.length;
    }


    public static void visualizeElectricityPrices(){
        //ToDo: Visualisation method + add menu choice
    }


}