package chessValidator;

import chessValidator.manager.UcManager;
import chessValidator.models.InvalidMoveException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {

    //https://workat.tech/machine-coding/practice/design-chess-validator-to77d8oqpx2h
    public static void main(String[] args) {
        UcManager ucManager = new UcManager();
        //init board
        ucManager.initBoard();
        //print the initialized board
        printBoard(ucManager.getBoardForPrinting());
        while (true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String[] commands = scanner.nextLine().split(" ");
            try {
                ucManager.move(commands[0], commands[1]);
                printBoard(ucManager.getBoardForPrinting());
            } catch (InvalidMoveException e) {
                System.out.println("Invalid Move. Try Aagin.");
            }
        }
    }

    private static void printBoard(String[][] board) {
        for (String[] row : board) {
            System.out.println(Arrays.stream(row).collect(Collectors.joining(" ")));
        }
    }
}
