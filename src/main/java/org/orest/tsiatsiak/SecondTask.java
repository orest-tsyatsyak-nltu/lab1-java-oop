package org.orest.tsiatsiak;

import java.util.concurrent.ThreadLocalRandom;

public class SecondTask {

    private static final int ROWS = 10;

    private static final int COLS = 10;

    private static final int MIN_VALUE = -9;

    private static final int MAX_VALUE = 9;

    private static final String ANSI_RESET = "\u001B[0m";

    private static final String ANSI_RED = "\u001B[31m";

    private final int[][] matrix;


    public SecondTask() {
        matrix = new int[ROWS][];
        for (int i = 0; i < ROWS; i++) {
            matrix[i] = getRowWithRandomNumbers();
        }
    }

    private int[] getRowWithRandomNumbers() {
        int[] row = new int[COLS];
        for (int i = 0; i < COLS; i++) {
            row[i] = ThreadLocalRandom.current().nextInt(MIN_VALUE, MAX_VALUE + 1);
        }
        return row;
    }

    public void run() {
        System.out.println("\nMatrix:\n");
        printMatrix();
        System.out.println("Arithmetic mean " + countArithmeticMeanAmongNegativeNumbersBelowAuxiliaryLine());
    }

    private void printMatrix() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                boolean elementIsBelowAuxiliaryLine = i + j >= ROWS;
                if (elementIsBelowAuxiliaryLine) {
                    System.out.print(ANSI_RED + matrix[i][j] + "\t\t");
                } else {
                    System.out.print(ANSI_RESET + matrix[i][j] + "\t\t");
                }
            }
            System.out.println("\n\n");
        }
    }


    private double countArithmeticMeanAmongNegativeNumbersBelowAuxiliaryLine() {
        double negativeNumbersSum = 0;
        int numberOfNegativeNumbers = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int currentElement = matrix[i][j];
                boolean elementIsBelowAuxiliaryLine = i + j >= ROWS;
                if (elementIsBelowAuxiliaryLine && currentElement < 0) {
                    numberOfNegativeNumbers++;
                    negativeNumbersSum += currentElement;
                }
            }
        }
        if (numberOfNegativeNumbers == 0) {
            return 0;
        }
        return negativeNumbersSum / numberOfNegativeNumbers;
    }

}
