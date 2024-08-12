import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';

    private char[][] board = new char[ROWS][COLS];

    public ConnectFour() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    public boolean makeMove(int col, char player) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY) {
                board[i][col] = player;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(char player) {
        // Check horizontal, vertical, and diagonal directions
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (checkDirection(row, col, 1, 0, player) || // Horizontal
                    checkDirection(row, col, 0, 1, player) || // Vertical
                    checkDirection(row, col, 1, 1, player) || // Diagonal \
                    checkDirection(row, col, 1, -1, player)) { // Diagonal /
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int row, int col, int rowDir, int colDir, char player) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConnectFour game = new ConnectFour();
        char currentPlayer = PLAYER_ONE;
        boolean gameWon = false;

        while (true) {
            game.printBoard();
            System.out.println("Player " + currentPlayer + ", enter a column (0-6): ");
            int col = scanner.nextInt();

            if (col < 0 || col >= COLS || !game.makeMove(col, currentPlayer)) {
                System.out.println("Invalid move, try again.");
                continue;
            }

            if (game.checkWin(currentPlayer)) {
                game.printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameWon = true;
                break;
            }

            currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
        }

        if (!gameWon) {
            System.out.println("It's a draw!");
        }

        scanner.close();
    }
}
