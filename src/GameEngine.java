import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private final Random rand = new Random();
    private final String[] gameBoard = new String[16];

    // array storage for positions played by the user
    private final ArrayList<Integer> playerPosition = new ArrayList<>();

    // array storage for positions played by the CPU
    private final ArrayList<Integer> computerPosition = new ArrayList<>();

    // array storage for positions played by the human
    private final ArrayList<Integer> player2Position = new ArrayList<>();

    // String to break the loop in case of winning, and also to print out the results
    private String win = "";

    // counts moves done by both players to check winning after the 4th move, and also to determine draw in game
    private static int moveCount = 0;
    private final int[] randArr = new int[3];
    static Scanner input = new Scanner(System.in);


    /**
     * generates 3 non-repetitive random numbers to lock 3 cells of the game board
     *
     * @param randArr storage array for random numbers
     */
    private void generateRandomLucks(int[] randArr) {
        for (int i = 0; i < 3; i++) {
            randArr[i] = rand.nextInt(0, 15);
        }
        while (randArr[0] == randArr[1] || randArr[1] == randArr[2] || randArr[0] == randArr[2]) {
            for (int i = 0; i < 3; i++) {
                randArr[i] = rand.nextInt(0, 15);
            }
        }
        Arrays.sort(randArr);

        // displays '#' on locked cells
        int j = 0;
        for (int i = 0; i < 16; i++) {
            if (gameBoard[i].equals(String.valueOf(randArr[j]))) {
                gameBoard[i] = "#";
                j++;
                if (j == 3) {
                    break;
                }
            }
        }
    }

    /**
     * prints the 4x4 game board
     *
     * @param gameBoard Sting of 16 elements to display the game board
     */
    private void printGameBoard(String[] gameBoard) {
        System.out.printf("%-2s%s%-2s%s%-2s%s%-2s%n", gameBoard[0], " | ", gameBoard[1], " | ", gameBoard[2], " | ", gameBoard[3]);
        System.out.println("-----------------");
        System.out.printf("%-2s%s%-2s%s%-2s%s%-2s%n", gameBoard[4], " | ", gameBoard[5], " | ", gameBoard[6], " | ", gameBoard[7]);
        System.out.println("-----------------");
        System.out.printf("%-2s%s%-2s%s%-2s%s%-2s%n", gameBoard[8], " | ", gameBoard[9], " | ", gameBoard[10], " | ", gameBoard[11]);
        System.out.println("-----------------");
        System.out.printf("%-2s%s%-2s%s%-2s%s%-2s%n", gameBoard[12], " | ", gameBoard[13], " | ", gameBoard[14], " | ", gameBoard[15]);
    }

    /**
     * resets game parameters for further playing
     * gives value to the cells of the game board from 0 to 15
     */
    public void gameSetUp() {
        System.out.print("\033[H\033[2J");
        win = "";
        moveCount = 0;
        playerPosition.removeAll(playerPosition);
        computerPosition.removeAll(computerPosition);

        for (int i = 0; i < 16; i++) {
            gameBoard[i] = String.valueOf(i);
        }
        generateRandomLucks(randArr);
        printGameBoard(gameBoard);
    }

    /**
     * places the positions played by each player
     *
     * @param gameBoard given to the method to set value to each cell
     * @param pos       adds the played position to array
     * @param user      determines either to use 'X' or 'O' as a symbol
     */
    private void placePiece(String[] gameBoard, int pos, String user) {
        String symbol = "";

        switch (user) {
            case "player" -> {
                symbol = "X";
                playerPosition.add(pos);
            }
            case "computer" -> {
                symbol = "O";
                computerPosition.add(pos);
            }
            case "human" -> {
                symbol = "O";
                player2Position.add(pos);
            }
        }

        switch (pos) {
            case 0 -> gameBoard[0] = symbol;
            case 1 -> gameBoard[1] = symbol;
            case 2 -> gameBoard[2] = symbol;
            case 3 -> gameBoard[3] = symbol;
            case 4 -> gameBoard[4] = symbol;
            case 5 -> gameBoard[5] = symbol;
            case 6 -> gameBoard[6] = symbol;
            case 7 -> gameBoard[7] = symbol;
            case 8 -> gameBoard[8] = symbol;
            case 9 -> gameBoard[9] = symbol;
            case 10 -> gameBoard[10] = symbol;
            case 11 -> gameBoard[11] = symbol;
            case 12 -> gameBoard[12] = symbol;
            case 13 -> gameBoard[13] = symbol;
            case 14 -> gameBoard[14] = symbol;
            case 15 -> gameBoard[15] = symbol;
        }
    }

    /**
     * checks if each player has won the game by generating 3 to 7 Strings
     * if the player has played the corner cells (0 - 3 - 12 - 15) there are 3 ways to win the game, therefor 3 Strings will be generated
     * if the player has played the outer cells (1 - 2 - 4 - 7 - 8 - 11 - 13 - 14) there are 4 ways to win, therefor 4 Strings will be generated
     * if the player has played the inner cells (5 - 6 - 9 - 10) there are 7 ways to win the game, therefor 7 Strings will be generated
     * Strings will be checked for either a win, or a draw in game
     *
     * @param gameBoard given to method to generate the Strings
     * @param pos       position played by each player
     * @param win       determines winning or draw
     * @return a String to break the loop in game and print out the results of it
     */
    private String checkWinner(String[] gameBoard, int pos, String win) {
        String firstLine = "";
        String secondLine = "";
        String thirdLine = "";
        String fourthLine = "";
        String fifthLine = "";
        String sixthLine = "";
        String seventhLine = "";

        moveCount++;

        if ((moveCount > 4) && (moveCount < 14)) {
            switch (pos) {
                case 0 -> {
                    firstLine = gameBoard[0] + gameBoard[1] + gameBoard[2];
                    secondLine = gameBoard[0] + gameBoard[5] + gameBoard[10];
                    thirdLine = gameBoard[0] + gameBoard[4] + gameBoard[8];
                }
                case 1 -> {
                    firstLine = gameBoard[1] + gameBoard[2] + gameBoard[3];
                    secondLine = gameBoard[1] + gameBoard[6] + gameBoard[11];
                    thirdLine = gameBoard[1] + gameBoard[5] + gameBoard[9];
                    fourthLine = gameBoard[0] + gameBoard[1] + gameBoard[2];
                }
                case 2 -> {
                    firstLine = gameBoard[0] + gameBoard[1] + gameBoard[2];
                    secondLine = gameBoard[2] + gameBoard[6] + gameBoard[10];
                    thirdLine = gameBoard[2] + gameBoard[5] + gameBoard[8];
                    fourthLine = gameBoard[1] + gameBoard[2] + gameBoard[3];
                }
                case 3 -> {
                    firstLine = gameBoard[3] + gameBoard[7] + gameBoard[11];
                    secondLine = gameBoard[3] + gameBoard[6] + gameBoard[9];
                    thirdLine = gameBoard[1] + gameBoard[2] + gameBoard[3];
                }
                case 4 -> {
                    firstLine = gameBoard[4] + gameBoard[8] + gameBoard[12];
                    secondLine = gameBoard[4] + gameBoard[5] + gameBoard[6];
                    thirdLine = gameBoard[4] + gameBoard[9] + gameBoard[14];
                    fourthLine = gameBoard[0] + gameBoard[4] + gameBoard[8];
                }
                case 5 -> {
                    firstLine = gameBoard[5] + gameBoard[6] + gameBoard[7];
                    secondLine = gameBoard[5] + gameBoard[9] + gameBoard[13];
                    thirdLine = gameBoard[5] + gameBoard[10] + gameBoard[15];
                    fourthLine = gameBoard[4] + gameBoard[5] + gameBoard[6];
                    fifthLine = gameBoard[1] + gameBoard[5] + gameBoard[9];
                    sixthLine = gameBoard[0] + gameBoard[5] + gameBoard[10];
                    seventhLine = gameBoard[2] + gameBoard[5] + gameBoard[8];
                }
                case 6 -> {
                    firstLine = gameBoard[5] + gameBoard[6] + gameBoard[7];
                    secondLine = gameBoard[6] + gameBoard[10] + gameBoard[14];
                    thirdLine = gameBoard[6] + gameBoard[9] + gameBoard[12];
                    fourthLine = gameBoard[4] + gameBoard[5] + gameBoard[6];
                    fifthLine = gameBoard[2] + gameBoard[6] + gameBoard[10];
                    sixthLine = gameBoard[3] + gameBoard[6] + gameBoard[9];
                    seventhLine = gameBoard[1] + gameBoard[6] + gameBoard[11];
                }
                case 7 -> {
                    firstLine = gameBoard[5] + gameBoard[6] + gameBoard[7];
                    secondLine = gameBoard[7] + gameBoard[11] + gameBoard[15];
                    thirdLine = gameBoard[7] + gameBoard[10] + gameBoard[13];
                    fourthLine = gameBoard[3] + gameBoard[7] + gameBoard[11];
                }
                case 8 -> {
                    firstLine = gameBoard[8] + gameBoard[9] + gameBoard[10];
                    secondLine = gameBoard[8] + gameBoard[5] + gameBoard[2];
                    thirdLine = gameBoard[0] + gameBoard[4] + gameBoard[8];
                    fourthLine = gameBoard[4] + gameBoard[8] + gameBoard[12];
                }
                case 9 -> {
                    firstLine = gameBoard[8] + gameBoard[9] + gameBoard[10];
                    secondLine = gameBoard[9] + gameBoard[5] + gameBoard[1];
                    thirdLine = gameBoard[5] + gameBoard[9] + gameBoard[13];
                    fourthLine = gameBoard[9] + gameBoard[10] + gameBoard[11];
                    fifthLine = gameBoard[9] + gameBoard[6] + gameBoard[3];
                    sixthLine = gameBoard[12] + gameBoard[9] + gameBoard[6];
                    seventhLine = gameBoard[4] + gameBoard[9] + gameBoard[14];
                }
                case 10 -> {
                    firstLine = gameBoard[8] + gameBoard[9] + gameBoard[10];
                    secondLine = gameBoard[2] + gameBoard[6] + gameBoard[10];
                    thirdLine = gameBoard[0] + gameBoard[5] + gameBoard[10];
                    fourthLine = gameBoard[9] + gameBoard[10] + gameBoard[11];
                    fifthLine = gameBoard[6] + gameBoard[10] + gameBoard[14];
                    sixthLine = gameBoard[5] + gameBoard[10] + gameBoard[15];
                    seventhLine = gameBoard[7] + gameBoard[10] + gameBoard[13];
                }
                case 11 -> {
                    firstLine = gameBoard[3] + gameBoard[7] + gameBoard[11];
                    secondLine = gameBoard[7] + gameBoard[11] + gameBoard[15];
                    thirdLine = gameBoard[9] + gameBoard[10] + gameBoard[11];
                    fourthLine = gameBoard[1] + gameBoard[6] + gameBoard[11];
                }
                case 12 -> {
                    firstLine = gameBoard[8] + gameBoard[4] + gameBoard[12];
                    secondLine = gameBoard[12] + gameBoard[13] + gameBoard[14];
                    thirdLine = gameBoard[12] + gameBoard[6] + gameBoard[9];
                }
                case 13 -> {
                    firstLine = gameBoard[13] + gameBoard[14] + gameBoard[15];
                    secondLine = gameBoard[12] + gameBoard[13] + gameBoard[14];
                    thirdLine = gameBoard[13] + gameBoard[9] + gameBoard[5];
                    fourthLine = gameBoard[13] + gameBoard[10] + gameBoard[7];
                }
                case 14 -> {
                    firstLine = gameBoard[13] + gameBoard[14] + gameBoard[15];
                    secondLine = gameBoard[12] + gameBoard[13] + gameBoard[14];
                    thirdLine = gameBoard[6] + gameBoard[10] + gameBoard[14];
                    fourthLine = gameBoard[14] + gameBoard[9] + gameBoard[4];
                }
                case 15 -> {
                    firstLine = gameBoard[7] + gameBoard[11] + gameBoard[15];
                    secondLine = gameBoard[15] + gameBoard[13] + gameBoard[14];
                    thirdLine = gameBoard[5] + gameBoard[10] + gameBoard[15];
                }
            }
            if (firstLine.equals("XXX") || secondLine.equals("XXX") || thirdLine.equals("XXX") || fourthLine.equals("XXX") || fifthLine.equals("XXX") || sixthLine.equals("XXX") || seventhLine.equals("XXX")) {
                win = "X";
            } else if (firstLine.equals("OOO") || secondLine.equals("OOO") || thirdLine.equals("OOO") || fourthLine.equals("OOO") || fifthLine.equals("OOO") || sixthLine.equals("OOO") || seventhLine.equals("OOO")) {
                win = "O";
            } else if ((moveCount == 13) && (!win.equals("X")) && (!win.equals("O"))) {
                win = "draw";
            }
        }

        return win;
    }

    /**
     * while loop to play with the CPU
     * uses string @param win to break the loop from @method checkWinner
     * prints out the final result
     */
    public void playWithComputer() {
        while (win.equals("")) {

            // gets input from the player
            System.out.printf("%n%s%n", "> Enter your position (0 - 15):");
            int playerPos = input.nextInt();

            // checks for invalid inputs
            while (playerPosition.contains(playerPos) || computerPosition.contains(playerPos) || playerPos == randArr[0] || playerPos == randArr[1] || playerPos == randArr[2] || playerPos > 15 || playerPos < 0) {
                System.out.printf("%n%s%n", "> Invalid position! Please try again...");
                playerPos = input.nextInt();
            }

            // places player position on the game board
            placePiece(gameBoard, playerPos, "player");

            // checks if the player has won the game
            win = checkWinner(gameBoard, playerPos, win);

            // breaks the loop in case of winning
            if (win.equals("draw") || win.equals("X") || win.equals("O")) {
                System.out.print("\033[H\033[2J"); // clear the screen
                printGameBoard(gameBoard);
                break;
            }

            // gets random number from the computer
            int computerPos = rand.nextInt(0, 15);

            // checks to generate a non-repetitive random number
            while (playerPosition.contains(computerPos) || computerPosition.contains(computerPos) || computerPos == randArr[0] || computerPos == randArr[1] || computerPos == randArr[2]) {
                computerPos = rand.nextInt(0, 15);
            }

            // places the computer position on the game board
            placePiece(gameBoard, computerPos, "computer");

            // checks if computer has won the game
            win = checkWinner(gameBoard, computerPos, win);

            System.out.print("\033[H\033[2J"); // clear the screen
            printGameBoard(gameBoard);
        }

        // prints out the result of the game
        switch (win) {
            case "X" -> System.out.printf("%n%s%n", "Winner winner chicken dinner!");
            case "O" -> System.out.printf("%n%s%n", "CPU won, better luck next time!");
            case "draw" -> System.out.printf("%n%s%n", "Surprise, It's a draw!");
        }
    }

    /**
     * while loop to play with another human
     * uses string @param win to break the loop from @method checkWinner
     * prints out the final result
     */
    public void playWithHuman() {
        while (win.equals("")) {

            // gets input from player 'X'
            System.out.printf("%n%s%n", "> Player 'X' position (0 - 15):");
            int playerPos = input.nextInt();

            // checks for invalid inputs
            while (playerPosition.contains(playerPos) || player2Position.contains(playerPos) || playerPos == randArr[0] || playerPos == randArr[1] || playerPos == randArr[2] || playerPos > 15 || playerPos < 0) {
                System.out.printf("%n%s%n", "> Invalid position! Please try again");
                playerPos = input.nextInt();
            }

            // places player 'X' position on the game board
            placePiece(gameBoard, playerPos, "player");

            System.out.print("\033[H\033[2J"); // clear the screen
            printGameBoard(gameBoard);

            // checks if player 'X' has won the game
            win = checkWinner(gameBoard, playerPos, win);

            // breaks the loop in case of winning
            if (win.equals("draw") || win.equals("X") || win.equals("O")) {
                System.out.print("\033[H\033[2J"); // clear the screen
                printGameBoard(gameBoard);
                break;
            }

            // gets input from player 'O'
            System.out.printf("%n%s%n", "> Player 'O' position (0 - 15):");
            int player2Pos = input.nextInt();

            // checks for invalid inputs
            while (playerPosition.contains(player2Pos) || player2Position.contains(player2Pos) || player2Pos == randArr[0] || player2Pos == randArr[1] || player2Pos == randArr[2]) {
                System.out.printf("%n%s%n", "> Invalid position! Please try again");
                player2Pos = input.nextInt();
            }

            // places player 'O' position on the game board
            placePiece(gameBoard, player2Pos, "human");

            // checks if player 'O' has won the game
            win = checkWinner(gameBoard, player2Pos, win);

            System.out.print("\033[H\033[2J"); // clear the screen
            printGameBoard(gameBoard);
        }

        // prints out the result of the game0
        switch (win) {
            case "X" -> System.out.printf("%n%s%n", "Congrats, Player 'X' wins!");
            case "O" -> System.out.printf("%n%s%n", "Congrats, Player 'O' wins!");
            case "draw" -> System.out.printf("%n%s%n", "Surprise, It's a draw!");
        }
    }
}
