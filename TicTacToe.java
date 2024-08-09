import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to my game of Tic Tac Toe! To play, type 'play.' To quit, type q.");
        System.out.print("> ");
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("q")) {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            else if (input.equals("play")) {
                play();
                System.out.println("Thanks for playing! Play again? Type 'play' to play again or 'q' to quit.");
                System.out.print("> ");
            }
            else {
                System.out.println("Invalid input. Please type 'play' to play or 'q' to quit.");
                System.out.print("> ");
            }
        }
    }

    static void makeGrid() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println("       |       |       ");
            }
            System.out.println("-----------------------");
        }
        for (int j = 0; j < 2; j++) {
            System.out.println("       |       |       ");
        }
    }

    static void updateRows(Square[] board, int start, int row) {
        int i;
        String symbol;
        for (i = start; i < start + 2; i++) {
            if (board[i].isOccupied()) {
                symbol = Square.getSymbol(board[i]);
                System.out.print("   " + symbol + "   |");
            } else {
                System.out.print("       |");
            }
        }
        if (board[i].isOccupied()) {
            symbol = Square.getSymbol(board[i]);
            System.out.println("   " + symbol + "   ");
        } else {
            System.out.println("       ");
        }
        if (row != 3) {
            System.out.println("       |       |       ");
            System.out.println("-----------------------");
        } else {
            System.out.println("       |       |       ");
        }
    }

    static void updateBoard(Square[] board) {
        updateRows(board, 0, 1);
        updateRows(board, 3, 2);
        updateRows(board, 6, 3);
    }

    public static boolean checkWin(Square[] board, int position) {
        String playerSymbol = Square.getSymbol(board[position]);
        if (position % 2 == 0) {
            if (checkDiags(board, position, playerSymbol)) {
                return true;
            }
        }
        if (checkRows(board, position, playerSymbol)) {
            return true;
        }
        return checkCols(board, position, playerSymbol);
    }

    static boolean scanRows(Square[] board, int start, String symbol) {
        for (int i = start; i < start + 3; i++) {
            if (!Square.getSymbol(board[i]).equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    static boolean checkRows(Square[] board, int position, String symbol) {
        if (position == 0 || position == 1 || position == 2) {
            return scanRows(board, 0, symbol);
        } else if (position == 3 || position == 4 || position == 5) {
            return scanRows(board, 3, symbol);
        } else {
            return scanRows(board, 6, symbol);
        }
    }

    static boolean scanCols(Square[] board, int start, String symbol) {
        for (int i = start; i <= start + 6; i += 3) {
            if (!Square.getSymbol(board[i]).equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    static boolean checkCols(Square[] board, int position, String symbol) {
        if (position == 0 || position == 3 || position == 6) {
            return scanCols(board, 0, symbol);
        } else if (position == 1 || position == 4 || position == 7) {
            return scanCols(board, 1, symbol);
        } else {
            return scanCols(board, 2, symbol);
        }
    }

    static boolean scanLeftRightDiag(Square[] board, String symbol) {
        for (int i = 0; i <= 8; i += 4) {
            if (!Square.getSymbol(board[i]).equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    static boolean scanRightLeftDiag(Square[] board, String symbol) {
        for (int i = 2; i <= 6; i += 2) {
            if (!Square.getSymbol(board[i]).equals(symbol)) {
                return false;
            }
        }
        return true;
    }

    static boolean checkDiags(Square[] board, int position, String symbol) {
        if (position == 4) {
            if (!scanLeftRightDiag(board, symbol)) {
                return scanRightLeftDiag(board, symbol);
            } else {
                return true;
            }
        } else if (position == 0 || position == 8) {
            return scanLeftRightDiag(board, symbol);
        } else {
            return scanRightLeftDiag(board, symbol);
        }
    }

    static void init(Square[] board) {
        for (int i = 0; i < 9; i++) {
            Square square = new Square();
            board[i] = square;
        }
    }

    static boolean boardIsFull(Square[] board) {
        for (int i = 0; i < 9; i++) {
            if (!board[i].isOccupied()) {
                return false;
            }
        }
        return true;
    }

    static void play() {
        System.out.println("Playing Tic Tac Toe!");
        System.out.println("Choosing who goes first...");
        Random rand = new Random();
        int starter = rand.nextInt(2) + 1;
        System.out.print("Player " + starter + " will go first and be X's.");
        int second;
        if (starter == 1) {
            second = 2;
        } else {
            second = 1;
        }
        System.out.println(" Player " + second + " is O's.");
        Scanner gameScanner = new Scanner(System.in);
        Square[] board = new Square[9];
        makeGrid();
        init(board);
        boolean game = true;
        int turn = starter;
        while (game) {
            System.out.println("Player " + turn + ", choose a position 1-9, 1 being the top left, 2 being the top middle, etc. Type q to quit.");
            System.out.print("> ");
            while (gameScanner.hasNext()) {
                if (! gameScanner.hasNextInt()) {
                    String input = gameScanner.next();
                    if (input.equals("q")) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }
                    System.out.println("Invalid input. Please enter a number 1-9 or 'q' to quit.");
                    System.out.print("> ");
                } else {
                    int move = gameScanner.nextInt();
                    if (move > 9 || move < 1) {
                        System.out.println("Invalid input. Please enter a number 1-9 or 'q' to quit.");
                        System.out.print("> ");
                    } else {
                        if (board[move - 1].isOccupied()) {
                            System.out.println("Square is occupied. Please choose a different square.");
                            System.out.print("> ");
                        } else {
                            board[move - 1].fill(turn, starter);
                            if (turn == starter) {
                                System.out.println("Player " + turn + " placed an 'X' in square " + move + ".");
                            } else {
                                System.out.println("Player " + turn + " placed an 'O' in square " + move + ".");
                            }
                            updateBoard(board);

                            if (checkWin(board, move - 1)) {
                                System.out.println("Player " + turn + " wins!");
                                game = false;
                            } else {
                                if (boardIsFull(board)) {
                                    System.out.println("Tie.");
                                    game = false;
                                } else if (turn == starter) {
                                    turn = second;
                                } else {
                                    turn = starter;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
