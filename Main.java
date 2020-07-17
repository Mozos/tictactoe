package tictactoe;

import java.util.Scanner;

public final class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Field field = new Field();
    private static char player = 'X';

    private static void playTurn() {
        boolean success = false;
        do {
            System.out.print("Enter coordinate: ");

            int x = 0;
            int y = 0;
            try {
                x = SCANNER.nextInt();
                y = SCANNER.nextInt();
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }

            if (validCoordinate(x, y)) {
                success = field.playCoordinate(x, y, player);
                if (success) {
                    player = player == 'X' ? 'O' : 'X';
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        } while (!success);
        System.out.println(field.toString());
    }

    private static boolean validCoordinate(int x, int y) {
        boolean valid = true;

        if (x < 0 || x > 3) {
            valid = false;
        }
        if (valid) {
            if (y < 0 || y > 3) {
                valid = false;
            }
        }

        if (!valid) {
            System.out.println("Coordinate should be 1 to 3!");
        }

        return  valid;
    }

    public static void main(String[] args) {
        System.out.println(field.toString());
        String status = "";
        do {
            playTurn();
            status = field.checkStatus();
        } while (Field.GAME_NOT_FINISHED.equals(status));
        System.out.println(status);
    }
}
