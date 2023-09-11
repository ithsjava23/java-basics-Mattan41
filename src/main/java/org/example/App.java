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
            choice = readChoice(sc);
            switch (choice) {
                case "1" -> inputElectricityPrice(electricityPrice, sc);
                case "2" -> {
                    minValue(electricityPrice);
                    maxValue(electricityPrice);
                    averageValue(electricityPrice);
                }

                case "3" -> sortElectricityPrices(electricityPrice);
                case "4" -> cheapestTimeToCharge(electricityPrice);
                //case "5" -> visualizeElectricityPrices();
            }
        }


    }

    public static int readValue(Scanner sc) {
        return Integer.parseInt(sc.nextLine());
    }
    public static String readChoice(Scanner sc) {
        return sc.nextLine();
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

            boolean success = false;
            while (!success) {
                System.out.print("Ange elpris i öre för timme " + i + "-" + (i + 1) + ":\n");

                try {
                    electricityPrice[i][1] = readValue(sc);
                    if (electricityPrice[i][1] > 0) {
                        success = true;
                    }
                    else if (electricityPrice[i][1] <= 0) {
                            success = true;
                    }


                } catch (NumberFormatException e) {
                    System.out.print("Felaktigt format. Försök igen.\n");
                }
            }
            electricityPrice[i][0] = (i);
        }
    }

    public static void maxValue(int[][] electricityPrice) {
        int max = Integer.MIN_VALUE;
        int maxRow;
        for (int[] ints : electricityPrice) {
            if (ints[1] > max) {
                max = ints[1];
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
        for (int[] ints : electricityPrice) {
            if (ints[1] < min) {
                min = ints[1];

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

        String str = getStringAverageFormat(average);
        System.out.print("Medelpris: "+ str + " öre/kWh\n");
    }

    public static String getStringAverageFormat(double average) {
        return String.format("%.2f", average).replace(".", ",");
    }
    public static String getStringAverageOneDecimalFormat(double average) {
        return String.format("%.1f", average).replace(".", ",");
    }


    public static void sortElectricityPrices(int[][] electricityPrice) {
        for (int i = 0; i < electricityPrice.length; i++) {
            for (int j = i + 1; j < electricityPrice.length; j++) {
                if (electricityPrice[i][1] >= electricityPrice[j][1]) {
                    int[] temp = electricityPrice[i];
                    electricityPrice[i] = electricityPrice[j];
                    electricityPrice[j] = temp;
                }
            }
        }

        for (int i = electricityPrice.length - 1; i >= 0; i--) {
            String time = String.format("%02d", electricityPrice[i][0]) + "-" + String.format("%02d", electricityPrice[i][0]+1);
            String price = String.format("%d",electricityPrice[i][1]);
            System.out.print(time + " " + price + " öre\n");
        }


    }

    public static void cheapestTimeToCharge(int[][] electricityPrice){

        int[][] cheapCharge = new int[4][2];

            int max = Integer.MAX_VALUE;
        for (int[] ints : cheapCharge) {
            Arrays.fill(ints, max);
        }

        int[][] tempArray = new int[cheapCharge.length][cheapCharge[0].length];

            for (int i = 0; i < cheapCharge.length; i++) {
                tempArray[i] = cheapCharge[i].clone();
            }

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
        String str = getStringAverageOneDecimalFormat(average);
            System.out.print("Påbörja laddning klockan " + time + "\nMedelpris 4h: " + str + " öre/kWh\n");

    }
    public static double getAverageDouble(int[][] electricityPrice) {
        double sum = 0;
        for (int[] ints : electricityPrice) {
            sum += ints[1];
        }
        return sum / electricityPrice.length;
    }

}