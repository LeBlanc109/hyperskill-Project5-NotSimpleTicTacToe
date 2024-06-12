package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        /*
        String s = scanner.next();
        scanner.nextLine();

         */

        char[][] board = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        printBoard(board);

        char currentPlayer = 'X';
        boolean gameIsValid = false;

        while (!gameIsValid) {
            String coordinateInput = scanner.nextLine().trim();
            String[] parts = coordinateInput.split(" ");

            int x = 0;
            int y = 0;

            try{
                y = Integer.parseInt(parts[0]) - 1;
                x = Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e){
                System.out.println("You should enter numbers!");
                continue;
            }
            if(x < 0 || x > 2 || y < 0 || y > 2){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if(board[y][x] != '_'){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[y][x] = currentPlayer;
            printBoard(board);

            String result = analyzeGame(board);
            if(!result.equals("Game not finished")){
                System.out.println(result);
                gameIsValid = true;
            }


            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }
    private static void printBoard(char[][] board){

        //Beginning ---
        System.out.println("---------");

        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");

            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);

                if(j < board[i].length - 1){
                    System.out.print(" ");
                }
            }
            System.out.println(" |");
        }
        //End ---
        System.out.println("---------");
    }

    private static String analyzeGame(char[][] board){
        //check conditions of board
        boolean xWins = false;
        boolean oWins = false;
        int countX = 0;
        int countO = 0;

        // Check rows & columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == 'X' && board[i][1] == 'X' && board[i][2] == 'X') {
                xWins = true;
            }
            if (board[i][0] == 'O' && board[i][1] == 'O' && board[i][2] == 'O') {
                oWins = true;
            }
            if (board[0][i] == 'X' && board[1][i] == 'X' && board[2][i] == 'X') {
                xWins = true;
            }
            if (board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == 'O') {
                oWins = true;
            }
        }

        // Check diagonals for a win
        if ((board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') ||
                (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X')) {
            xWins = true;
        }
        if ((board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') ||
                (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O')) {
            oWins = true;
        }

        // Count X's and O's on the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                } else if (board[i][j] == 'O') {
                    countO++;
                }
            }
        }

        // Determine game status
        if (xWins && oWins) {
            return "Impossible"; // Both X and O cannot win simultaneously
        } else if (Math.abs(countX - countO) >= 2) {
            return "Impossible"; // Difference between X and O count is 2 or more
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (countX + countO == 9) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

}
