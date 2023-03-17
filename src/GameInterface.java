import java.util.Scanner;

public class GameInterface {
    static Scanner input = new Scanner(System.in);
    GameEngine gameEngine = new GameEngine();


    /**
     * prints out menu of the game for user interface
     * ASCII art has been used to display the name of the game
     */
    public void gameMenu() {
        System.out.printf("%s%s%n%n%s%n%n%s%n", """

                                                                                                                        \s
                                                                                                                        \s
                    ___                                 ___                                    ___                      \s
                  ,--.'|_   ,--,                      ,--.'|_                                ,--.'|_                    \s
                  |  | :,',--.'|                      |  | :,'                               |  | :,'   ,---.           \s
                  :  : ' :|  |,                       :  : ' :                               :  : ' :  '   ,'\\          \s
                .;__,'  / `--'_      ,---.          .;__,'  /   ,--.--.     ,---.          .;__,'  /  /   /   |  ,---.  \s
                |  |   |  ,' ,'|    /     \\         |  |   |   /       \\   /     \\         |  |   |  .   ; ,. : /     \\ \s
                :__,'| :  '  | |   /    / '         :__,'| :  .--.  .-. | /    / '         :__,'| :  '   | |: :/    /  |\s
                  '  : |__|  | :  .    ' /            '  : |__ \\__\\/: . ..    ' /            '  : |__'   | .; .    ' / |\s
                  |  | '.''  : |__'   ; :__           |  | '.'|," .--.; |'   ; :__           |  | '.'|   :    '   ;   /|\s
                  ;  :    |  | '.''   | '.'|          ;  :    /  /  ,.  |'   | '.'|          ;  :    ;\\   \\  /'   |  / |\s
                  |  ,   /;  :    |   :    :          |  ,   ;  :   .'   |   :    :          |  ,   /  `----' |   :    |\s
                   ---`-' |  ,   / \\   \\  /            ---`-'|  ,     .-./\\   \\  /            ---`-'           \\   \\  / \s
                           ---`-'   `----'                    `--`---'     `----'                               `----'  \s
                                                                                                                        \s
                """, "1.Play with the computer", "2.Play with your friend", "3.Exit");
    }

    /**
     * user can continue or restart the game by pressing Enter key
     */
    private void pressEnterToContinue() {
        System.out.printf("%n%s%n", "Press Enter key to continue...");
        try {
            input.nextLine();
            input.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets input from the user
     * checks in the case of invalid input
     *
     * @return the input for the menu
     */
    private int getInput() {
        System.out.printf("%n%s%n", "> Please enter your command");
        int inputKey = input.nextInt();
        while ((inputKey > 3) || (inputKey < 1)) {
            System.out.println("> Invalid command, try again...");
            inputKey = input.nextInt();
        }

        return inputKey;
    }

    /**
     * executes menu of the game for user interface
     */
    public void menuExecution() {
        int inputKey = getInput();
        while (inputKey != 3) {
            switch (inputKey) {
                case 1 -> {
                    gameEngine.gameSetUp();
                    gameEngine.playWithComputer();
                    pressEnterToContinue();
                }
                case 2 -> {
                    gameEngine.gameSetUp();
                    gameEngine.playWithHuman();
                    pressEnterToContinue();
                }
                default -> System.out.printf("%s%n", "Wrong Input!");
            }

            System.out.print("\033[H\033[2J");
            gameMenu();
            inputKey = getInput();
        }
    }
}