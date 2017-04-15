package com.company;

import java.util.Random;

public class Main {
    private enum Direction {
        LEFT, TOP, RIGHT, BOTTOM;

        private Direction next;

        static {
            LEFT.next = BOTTOM;
            TOP.next = LEFT;
            RIGHT.next = TOP;
            BOTTOM.next = RIGHT;
        }

        Direction getNext() {
            return next;
        }
    }

    private static final int N = 3;
    private static final int MATRIX_SIZE = 2 * N - 1;

    private static int[][] matrix;

    static {
        matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        Random random = new Random();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                matrix[i][k] = random.nextInt(89) + 10;
                System.out.printf(matrix[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.printf(matrix[MATRIX_SIZE / 2][MATRIX_SIZE / 2] + " ");
        printNext(MATRIX_SIZE / 2, MATRIX_SIZE / 2 + 1, Direction.TOP, 2);
    }

    private static void printNext(int row, int col, Direction direction, int count) {
        boolean isLast = false;
        if (direction == Direction.RIGHT && count == MATRIX_SIZE) {
            isLast = true;
            count--;
        }

        int newRow = row;
        int newCol = col;
        for (int offset = 0; offset < count; offset++) {
            switch (direction) {
                case LEFT:
                    newCol = col - offset;
                    break;
                case TOP:
                    newRow = row - offset;
                    break;
                case RIGHT:
                    newCol = col + offset;
                    break;
                case BOTTOM:
                    newRow = row + offset;
                    break;
            }
            System.out.printf(matrix[newRow][newCol] + " ");
        }

        switch (direction) {
            case LEFT:
                newRow++;
                break;
            case TOP:
                newCol--;
                if (count > 2) {
                    count++;
                }
                break;
            case RIGHT:
                newRow--;
                break;
            case BOTTOM:
                newCol++;
                count++;
                break;
        }

        if (!isLast) {
            printNext(newRow, newCol, direction.getNext(), count);
        }
    }
}
