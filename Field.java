package tictactoe;

import java.util.Arrays;

public class Field {

    static final String GAME_NOT_FINISHED = "Game not finished";
    private final char[][] field;

    Field() {
        field = new char[3][3];
        initializeCells();
    }

    private void initializeCells() {
        for (int row = field.length - 1; row >= 0; row--) {
            Arrays.fill(field[row], '_');
        }
    }

    boolean playCoordinate(int x, int y, char player) {
        boolean success = true;

        int row = 3 - y;
        int column = x - 1;

        if (field[row][column] == '_') {
            field[row][column] = player;
        } else {
            success = false;
        }

        return success;
    }

    String checkStatus() {
        String status = checkWin();
        if (status == null) {
            status = checkDraw();
        }
        if (status == null) {
            status = GAME_NOT_FINISHED;
        }
        return status;
    }

    private String checkWin() {
        String win = null;

        for (char[] row : field) { // Checking rows
            if (row[0] == row[1] && row[1] == row[2] && row[0] != '_') {
                win = row[0] + " wins";
            }
        }
        for (int column = 0; column < field[0].length; column++) { // Checking column
            if (field[0][column] == field[1][column] && field[1][column] == field[2][column]
                    && field[0][column] != '_') {
                win = field[0][column] + " wins";
            }
        }
        //Checking diagonal
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != '_') {
            win = field[0][0] + " wins";
        }
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != '_') {
            win = field[0][2] + " wins";
        }

        return win;
    }

    private String checkDraw() {
        String draw = "Draw";

        out:
        for (char[] row : field) {
            for (char column : row) {
                if (column == '_') {
                    draw = null;
                    break out;
                }
            }
        }

        return draw;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("---------\n");
        for (char[] rows : field) {
            for (int column = 0; column < rows.length; column++) {
                if (column == 0) {
                    string.append("| ");
                }
                string.append(rows[column]).append(' ');
            }
            string.append("|\n");
        }
        string.append("---------");

        return string.toString();
    }
}
