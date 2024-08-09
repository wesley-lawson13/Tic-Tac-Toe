public class Square {

    private String occupied;

    Square() {
        occupied = "---";
    }

    public boolean isOccupied() {
        return (!occupied.equals("---"));
    }

    public void fill(int playerFilling, int playerStarting) {
        if (playerFilling == playerStarting) {
            occupied = "X";
        } else {
            occupied = "O";
        }
    }

    public static String getSymbol(Square square) {
        return square.occupied;
    }
}
