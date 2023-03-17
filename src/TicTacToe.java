/**
 * tic-tac-toe non-GUI game that allows you to play in a 4x4 game board with 3 locked cells
 * playing with either human or AI
 * winner is the first to match 3 cells in order either vertically, horizontally or diagonally
 *
 * @author Zahra Rafiei
 */
public class TicTacToe {
    private static final GameInterface gameInterface = new GameInterface();

    /**
     * the main method in which the code gets executed
     *
     * @param args main method parameter
     */
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        gameInterface.gameMenu();
        gameInterface.menuExecution();
    }
}